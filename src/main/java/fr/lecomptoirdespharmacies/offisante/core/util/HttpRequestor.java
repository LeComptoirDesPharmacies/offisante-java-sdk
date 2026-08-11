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
            String content = response.body().string();

            // Offisante may answer with something that is not Json at all (gateway error page,
            // maintenance page). Fail here with the http status and what was actually received,
            // rather than letting the Json parser report an unusable "Unexpected character ('<')".
            if (!PayloadUtil.isJsonPayload(content)) {
                throw new RuntimeException(String.format(
                        "Offisante returned a non Json response (HTTP %d) for %s : %s",
                        response.code(), url, PayloadUtil.excerpt(content)
                ));
            }

            return content;
        } catch (NullPointerException e){
            // Body is empty
            throw new IllegalArgumentException("Response body is empty cannot get string from it", e);
        } catch (IOException e){
            // IO Exception
            throw new RuntimeException("An error occur when trying to post request", e);
        }
    }
}
