package fr.lecomptoirdespharmacies.offisante.core.util;

import okhttp3.*;
import java.io.IOException;
import java.util.Map;

/**
 * Class who execute HTTP request
 */
public class HttpRequestor implements HttpRequest {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    //TODO: better management of error
    @Override
    public String post(String url, String body, Map<String, String> header){
        // Set Body
        RequestBody rBody = RequestBody.create(JSON, body);

        // Create request builder
        Request.Builder rBuilder = new Request.Builder();

        // Set headers
        header.forEach(rBuilder::addHeader);
        // Set URL
        rBuilder.url(url);
        // Set Body
        rBuilder.post(rBody);

        // Build and execute request
        Request request = rBuilder.build();

        // Handle exception
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (NullPointerException e){
            // Body is empty
            throw new IllegalArgumentException();
        } catch (IOException ie){
            // IO Exception
            throw new RuntimeException();
        }
    }
}
