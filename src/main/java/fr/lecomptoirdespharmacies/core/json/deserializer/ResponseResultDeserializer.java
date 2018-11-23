package fr.lecomptoirdespharmacies.core.json.deserializer;

import fr.lecomptoirdespharmacies.entity.http.response.ResponseResult;

import java.util.Map;

public class ResponseResultDeserializer extends UniquePropertyPolymorphicDeserializer<ResponseResult>{

    public ResponseResultDeserializer(Map<String, Class<? extends ResponseResult>> registry) {
        super(ResponseResult.class, registry);
    }
}
