package fr.lecomptoirdespharmacies.entity.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.core.json.deserializer.LocalDateTimeDeserializer;
import fr.lecomptoirdespharmacies.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ResponseProduct extends BaseEntity {
    @JsonProperty("cip7")
    String cip7;

    @JsonProperty("code13")
    String cip13;

    @JsonProperty("label")
    String label;

    @JsonProperty("qty")
    Integer quantity;

    @JsonProperty("unit_price")
    BigDecimal unitPrice;
}
