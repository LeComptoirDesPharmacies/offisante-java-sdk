package fr.lecomptoirdespharmacies.offisante.core.util;

import java.util.Map;

public interface HttpRequest {
    /**
     * Execute JSON POST request
     * @param url       Url to request
     * @param body      Json Content of the request
     * @param header    Headers of the request
     * @return          Json response
     */
    String post(String url, String body, Map<String, String> header);
}
