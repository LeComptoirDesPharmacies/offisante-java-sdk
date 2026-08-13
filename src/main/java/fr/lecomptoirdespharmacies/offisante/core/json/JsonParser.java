package fr.lecomptoirdespharmacies.offisante.core.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Class to parse Json to Object
 */
public class JsonParser extends Json{

    /**
     * Maximum number of characters kept when the payload is quoted in an error message
     */
    private static final int EXCERPT_MAX_LENGTH = 200;

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
            throw new RuntimeException("Unable to parse entity to Json, received : " + excerpt(), e);
        } catch (JsonMappingException e){
            throw new RuntimeException("Unable to map json to entity, received : " + excerpt(), e);
        } catch (IOException e){
            throw new RuntimeException("I/O exception occur during json parsing, received : " + excerpt(), e);
        }
    }

    /**
     *          Single line and truncated view of the payload, so that a parsing failure
     *          tells what was actually received instead of only where it broke
     *
     * @return  Readable excerpt of the payload
     */
    private String excerpt(){
        return StringUtils.abbreviate(StringUtils.normalizeSpace(json), EXCERPT_MAX_LENGTH);
    }
}
