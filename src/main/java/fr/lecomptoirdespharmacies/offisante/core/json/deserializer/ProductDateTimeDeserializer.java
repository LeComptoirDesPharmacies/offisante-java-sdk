package fr.lecomptoirdespharmacies.offisante.core.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.DEFAULT_PRODUCT_DATE_TIME_PATTERN;


public class ProductDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern(DEFAULT_PRODUCT_DATE_TIME_PATTERN))
                .appendOptional(new DateTimeFormatterBuilder()
                        .append(DateTimeFormatter.ofPattern(".SSSSSS"))
                        .toFormatter()
                ).toFormatter();
        return LocalDateTime.parse(p.getText(), formatter);
    }
}
