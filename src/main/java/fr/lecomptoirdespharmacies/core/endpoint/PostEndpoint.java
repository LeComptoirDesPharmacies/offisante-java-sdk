package fr.lecomptoirdespharmacies.core.endpoint;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.json.CreateJsonParser;
import fr.lecomptoirdespharmacies.core.json.deserializer.ResponseResultDeserializer;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import fr.lecomptoirdespharmacies.core.json.JsonParser;
import fr.lecomptoirdespharmacies.core.util.HttpRequest;
import fr.lecomptoirdespharmacies.core.util.HttpRequestor;
import fr.lecomptoirdespharmacies.core.util.UrlUtil;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import fr.lecomptoirdespharmacies.entity.http.Body;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseResult;

import java.util.HashMap;

public class PostEndpoint implements Endpoint {

    final private HttpRequest requestor = new HttpRequestor();

    final private UrlUtil urlUtil = new UrlUtil();


    @Override
    public <T extends Body> T securePost(OffisanteApi api, Uri uri, RequestBody body, Class<T> responseCls,  ResponseResultDeserializer deserializer){

        String strBody = body.toJson();

        String url = urlUtil.getFullUrl(api, uri);

        String response = requestor.post(url, strBody, api.getTokenManager().getAuthorizationHeader());

        JsonParser parser = new CreateJsonParser(response)
                .withModule(new SimpleModule("ResponseModule"))
                .addDeserializer(ResponseResult.class, deserializer)
                .build();

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
