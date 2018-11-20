package fr.lecomptoirdespharmacies.core.util;

import fr.lecomptoirdespharmacies.entity.BaseEntity;

import java.io.IOException;
import java.util.Map;

public interface HttpRequest {
    String post(String url, String body, Map<String, String> header);
}
