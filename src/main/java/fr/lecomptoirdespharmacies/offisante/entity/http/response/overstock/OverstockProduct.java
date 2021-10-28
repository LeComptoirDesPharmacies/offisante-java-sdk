package fr.lecomptoirdespharmacies.offisante.entity.http.response.overstock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.ProductDateTimeDeserializer;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.ResponseProduct;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverstockProduct extends ResponseProduct {
    @JsonProperty("Forecast")
    Integer forecast;
    @JsonProperty("unsold")
    Integer unsold;
    @JsonProperty("last_sale")
    @JsonDeserialize(using = ProductDateTimeDeserializer.class)
    LocalDateTime lastSale;
}
