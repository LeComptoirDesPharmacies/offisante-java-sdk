package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.core.endpoint.PostEndpoint;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import lombok.Getter;

@Getter
abstract class BaseApi extends PostEndpoint {

    private OffisanteApi api;

    protected BaseApi(OffisanteApi api) {
        this.api = api;
    }

    /**
     *                      Send request to Offisante and update Token remaining
     * @param api           Offisante APi
     * @param uri           Uri with parameters
     * @param body          Content of the request
     * @param responseCls   Class to parse json in
     * @param <T>           Class that extend body
     * @return              Response casted in response class
     */
    @Override
    public <T extends Body> T securePost(OffisanteApi api, Uri uri, RequestBody body, Class<T> responseCls) {
        T response = super.securePost(api, uri, body, responseCls);
        api.getTokenManager().updateRemaining(response);
        return response;
    }
}
