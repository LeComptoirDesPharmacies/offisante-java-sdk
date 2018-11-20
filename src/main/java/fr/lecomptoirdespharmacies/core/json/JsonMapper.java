package fr.lecomptoirdespharmacies.core.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lecomptoirdespharmacies.entity.http.Jsonable;

public class JsonMapper extends Json {

    public <T extends Jsonable> String toJson(T entity){
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to parse entity to Json");
        }
    }
}
