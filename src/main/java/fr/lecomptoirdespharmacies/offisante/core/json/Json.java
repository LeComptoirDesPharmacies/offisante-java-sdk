package fr.lecomptoirdespharmacies.offisante.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;

abstract class Json {
    protected final ObjectMapper mapper = new ObjectMapper();
}
