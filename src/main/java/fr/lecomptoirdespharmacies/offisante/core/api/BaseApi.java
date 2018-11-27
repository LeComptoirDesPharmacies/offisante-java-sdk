package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.core.endpoint.PostEndpoint;
import fr.lecomptoirdespharmacies.offisante.core.manager.TimeManager;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import lombok.Getter;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.MALFORMED_TOKEN;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.TOKEN_RATE_LIMIT_REACHED;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.UNKNOWN_TOKEN;

@Getter
abstract class BaseApi extends PostEndpoint {

    private OffisanteApi api;

    private final TimeManager timeManager = new TimeManager(10, 1);

    private final static int MAX_RETRY = 5;

    protected BaseApi(OffisanteApi api) {
        this.api = api;
    }

    /**
     *                      Send request to Offisante and update Token remaining
     *
     * @param api           Offisante APi
     * @param uri           Uri with parameters
     * @param body          Content of the request
     * @param responseCls   Class to parse json
     * @param <T>           Class that extend body
     * @return              Response casted in response class
     */
    @Override
    public <T extends Body> T securePost(OffisanteApi api, Uri uri, RequestBody body, Class<T> responseCls) {
        return super.securePost(api, uri, body, responseCls);
    }

    /**
     *                      Execute post request
     *
     * @param uri           Uri with parameters
     * @param body          Content of the request
     * @param responseCls   Class to parse json
     * @param retry         Number of time request was executed
     * @param <T>           Class that extend body
     * @return              Response casted in response class
     */
    public  <T extends Body> T executePost(Uri uri, RequestBody body, Class<T> responseCls, int retry){
        T response = securePost(api, uri, body, responseCls);

        if(response.getCode() == null) {
            timeManager.resetMultiplier();
            return response;
        }

        // To avoid infinite request
        if (retry <= MAX_RETRY){
            return manageError(response, uri, body, responseCls, retry);
        }

        return response;
    }

    /**
     *                      Manage request when there is a token error redo request
     *                      with a new token if needed or delay request to pass rate limit
     *
     * @param response      Response casted in response class
     * @param uri           Uri with parameters
     * @param body          Content of the request
     * @param responseCls   Class to parse json
     * @param retry         Number of time request was executed
     * @param <T>           Class that extend body
     * @return              New response casted in response class
     */
    private  <T extends Body> T manageError(T response, Uri uri, RequestBody body, Class<T> responseCls, int retry){
        // Manage errors due to token
        switch (response.getCode()){
            case UNKNOWN_TOKEN:
                api.getTokenManager().generateToken();
                return executePost(uri, body, responseCls, retry+1);
            case TOKEN_RATE_LIMIT_REACHED:
                timeManager.sleep();
                return executePost(uri, body, responseCls, retry+1);
            case MALFORMED_TOKEN:
                api.getTokenManager().generateToken();
                return executePost(uri, body, responseCls, retry+1);
            default:
                timeManager.resetMultiplier();
                return response;
        }
    }
}
