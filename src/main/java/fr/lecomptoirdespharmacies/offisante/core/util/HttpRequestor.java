package fr.lecomptoirdespharmacies.offisante.core.util;

import okhttp3.*;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Class who execute HTTP request
 */
public class HttpRequestor implements HttpRequest {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final Integer CONNECT_TIMEOUT = 30;
    private static final Integer WRITE_TIMEOUT = 30;
    private static final Integer READ_TIMEOUT = 60;

    private OkHttpClient client = new OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build();


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
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (NullPointerException e){
            // Body is empty
            e.printStackTrace();
            throw new IllegalArgumentException();
        } catch (IOException ie){
            // IO Exception
            ie.printStackTrace();
            throw new RuntimeException();
        }
    }
}
