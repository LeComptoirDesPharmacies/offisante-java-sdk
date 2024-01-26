package fr.lecomptoirdespharmacies.offisante.core.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.lecomptoirdespharmacies.offisante.entity.Jsonable;

/**
 * Class to map object to Json
 */
public class JsonMapper extends Json {

    public <T extends Jsonable> String toJson(T entity){
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Unable to parse entity to Json", e);
        }
    }
}
