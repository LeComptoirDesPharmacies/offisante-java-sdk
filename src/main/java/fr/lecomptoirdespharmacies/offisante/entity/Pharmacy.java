package fr.lecomptoirdespharmacies.offisante.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.ProductDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pharmacy extends BaseEntity {
    @JsonProperty("cip")
    String cip;

    @JsonProperty("last_date_stock")
    @JsonDeserialize(using = ProductDateTimeDeserializer.class)
    LocalDateTime lastDateStock;
}
