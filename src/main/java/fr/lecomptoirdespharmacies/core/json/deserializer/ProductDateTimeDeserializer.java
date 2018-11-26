package fr.lecomptoirdespharmacies.core.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Locale;

import static fr.lecomptoirdespharmacies.core.Constant.DEFAULT_PRODUCT_DATE_PATTERN;


public class ProductDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DEFAULT_PRODUCT_DATE_PATTERN).withLocale(Locale.US);
        return LocalDateTime.parse(p.getText(), formatter);
    }
}
