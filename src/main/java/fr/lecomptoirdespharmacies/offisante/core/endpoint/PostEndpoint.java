package fr.lecomptoirdespharmacies.offisante.core.endpoint;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.core.json.CreateJsonParser;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.core.json.JsonParser;
import fr.lecomptoirdespharmacies.offisante.core.util.HttpRequest;
import fr.lecomptoirdespharmacies.offisante.core.util.HttpRequestor;
import fr.lecomptoirdespharmacies.offisante.core.util.UrlUtil;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;

import java.util.HashMap;

public class PostEndpoint implements Endpoint {

    final private HttpRequest requestor = new HttpRequestor();

    final private UrlUtil urlUtil = new UrlUtil();


    @Override
    public <T extends Body> T securePost(OffisanteApi api, Uri uri, RequestBody body, Class<T> responseCls){

        String strBody = body.toJson();

        String url = urlUtil.getFullUrl(api, uri);

        String response = requestor.post(url, strBody, api.getTokenManager().getAuthorizationHeader());

        JsonParser parser = new CreateJsonParser(response).build();

        return parser.parseJsonTo(responseCls);
    }

    @Override
    public <T extends Body> T requestToken(OffisanteApi api, Uri uri, UserCredentials body, Class<T> responseCls){

        String strBody = body.toJson();

        String url = urlUtil.getFullUrl(api, uri);

        String response = requestor.post(url, strBody, new HashMap<>());

        JsonParser parser = new CreateJsonParser(response).build();

        return parser.parseJsonTo(responseCls);
    }


}
