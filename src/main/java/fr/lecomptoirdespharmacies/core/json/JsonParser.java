package fr.lecomptoirdespharmacies.core.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import fr.lecomptoirdespharmacies.entity.http.Body;

import java.io.IOException;

public class JsonParser extends Json{

    public <T extends Body> T fromJson(String json, Class<T> responseCls){
        try {
            return mapper.readValue(json, responseCls);
        } catch (JsonParseException jpe){
            jpe.printStackTrace();
            throw new RuntimeException("Unable to parse entity to Json");
        } catch (JsonMappingException jme){
            jme.printStackTrace();
            throw new RuntimeException("Unable to map json to entity");
        } catch (IOException ie){
            ie.printStackTrace();
            throw new RuntimeException("I/O exception occur during json parsing");
        }
    }
}
