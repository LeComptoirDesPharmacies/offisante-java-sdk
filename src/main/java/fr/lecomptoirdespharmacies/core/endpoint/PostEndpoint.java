package fr.lecomptoirdespharmacies.core.endpoint;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.domain.Uri;
import fr.lecomptoirdespharmacies.core.json.JsonParser;
import fr.lecomptoirdespharmacies.core.util.HttpRequest;
import fr.lecomptoirdespharmacies.core.util.HttpRequestor;
import fr.lecomptoirdespharmacies.core.util.UrlUtil;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import fr.lecomptoirdespharmacies.entity.http.Body;

import java.util.HashMap;

public class PostEndpoint implements Endpoint {

    final private HttpRequest requestor = new HttpRequestor();

    final private UrlUtil urlUtil = new UrlUtil();

    final private JsonParser parser = new JsonParser();

    @Override
    public <T extends Body> T securePost(OffisanteApi api, Uri uri, T body, Class<T> responseCls){

        //TODO : create builder for body
        String strBody = body != null ? body.toJson() : "";

        String url = urlUtil.getFullUrl(api, uri);

        String response = requestor.post(url, strBody, api.getTokenManager().getAuthorizationHeader());

        return parser.fromJson(response, responseCls);
    }

    @Override
    public <T extends Body> T requestToken(OffisanteApi api, Uri uri, UserCredentials body, Class<T> responseCls){

        String strBody = body.toJson();

        String url = urlUtil.getFullUrl(api, uri);

        String response = requestor.post(url, strBody, new HashMap<>());

        return parser.fromJson(response, responseCls);
    }


}
