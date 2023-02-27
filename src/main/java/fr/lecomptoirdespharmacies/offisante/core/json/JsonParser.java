package fr.lecomptoirdespharmacies.offisante.core.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;

import java.io.IOException;

/**
 * Class to parse Json to Object
 */
public class JsonParser extends Json{

    private final String json;

    private final ObjectMapper mapper;

    public JsonParser(String json, ObjectMapper mapper) {
        this.json = json;
        this.mapper = mapper;
    }

    public <T extends Body> T parseJsonTo(Class<T> responseCls){
        try {
            return mapper.readValue(json, responseCls);
        } catch (JsonParseException e){
            throw new RuntimeException("Unable to parse entity to Json", e);
        } catch (JsonMappingException e){
            throw new RuntimeException("Unable to map json to entity", e);
        } catch (IOException e){
            throw new RuntimeException("I/O exception occur during json parsing", e);
        }
    }
}
