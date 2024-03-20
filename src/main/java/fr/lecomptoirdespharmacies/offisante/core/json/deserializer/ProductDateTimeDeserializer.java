package fr.lecomptoirdespharmacies.offisante.core.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.io.IOException;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.DEFAULT_PRODUCT_DATE_PATTERN;


public class ProductDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormat.forPattern(DEFAULT_PRODUCT_DATE_PATTERN))
                .appendOptional(new DateTimeFormatterBuilder()
                        .append(DateTimeFormat.forPattern(".SSSSSS"))
                        .toParser()
                ).toFormatter();
        return LocalDateTime.parse(p.getText(), formatter);
    }
}
