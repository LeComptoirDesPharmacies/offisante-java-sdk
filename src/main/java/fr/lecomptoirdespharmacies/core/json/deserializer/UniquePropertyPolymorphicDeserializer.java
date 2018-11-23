package fr.lecomptoirdespharmacies.core.json.deserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Deserializes documents without a specific field designated for Polymorphic Type
 * identification, when the document contains a field registered to be unique to that type
 *
 * @author robin
 * http://www.robinhowlett.com/blog/2015/03/19/custom-jackson-polymorphic-deserialization-without-type-metadata/
 *
 * Customize to be use with annotation
 * @see PropertyRegister
 */
public class UniquePropertyPolymorphicDeserializer<T> extends StdDeserializer<T> {

    private static final long serialVersionUID = 1L;

    private final Map<String, Class<? extends T>> registry;

    public UniquePropertyPolymorphicDeserializer(Class<T> clazz, Map<String, Class<? extends T>> registry) {
        super(clazz);
        this.registry = registry;
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        Class<? extends T> clazz = null;

        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode obj = (ObjectNode) mapper.readTree(jp);
        Iterator<Entry<String, JsonNode>> elementsIterator = obj.fields();

        while (elementsIterator.hasNext()) {
            Entry<String, JsonNode> element = elementsIterator.next();
            String name = element.getKey();
            if (registry.containsKey(name)) {
                clazz = registry.get(name);
                break;
            }
        }

        if (clazz == null) {
            throw ctxt.instantiationException(null, "No registered unique properties found for polymorphic deserialization");
        }

        return mapper.treeToValue(obj, clazz);
    }
}
