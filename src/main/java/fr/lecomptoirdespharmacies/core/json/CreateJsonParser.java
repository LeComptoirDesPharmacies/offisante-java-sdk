package fr.lecomptoirdespharmacies.core.json;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Json parser builder
 * Construct json mapper with module and serializer if needed
 * And build JsonParser with mapper
 */
public class CreateJsonParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public final String json;

    private SimpleModule module;

    public CreateJsonParser(String json){
        this.json = json;
    }

    public CreateJsonParser withModule(SimpleModule module){
        this.module = module;
        return this;
    }

    public <T> CreateJsonParser addDeserializer(Class<T> type, JsonDeserializer<? extends T> deserializer){
        if(module == null || type == null || deserializer == null) return this;
        module.addDeserializer(type, deserializer);
        return this;
    }

    public JsonParser build(){
        if(module != null) mapper.registerModule(module);

        return new JsonParser(
                json,
                mapper
        );
    }
}
