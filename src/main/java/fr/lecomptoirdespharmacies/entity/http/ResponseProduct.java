package fr.lecomptoirdespharmacies.entity.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.core.json.deserializer.LocalDateTimeDeserializer;
import fr.lecomptoirdespharmacies.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;

@Value
@EqualsAndHashCode(callSuper = true)
public class ResponseProduct extends BaseEntity {
    @JsonProperty("cip7")
    String cip7;

    @JsonProperty("cip13")
    String cip13;

    @JsonProperty("label")
    String label;

    @JsonProperty("qty")
    Integer quantity;

    @JsonProperty("unit_price")
    BigDecimal unitPrice;

    @JsonProperty("Last_Sale")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime lastSale;

}
