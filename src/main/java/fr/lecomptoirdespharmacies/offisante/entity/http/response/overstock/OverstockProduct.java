package fr.lecomptoirdespharmacies.offisante.entity.http.response.overstock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.ResponseProduct;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverstockProduct extends ResponseProduct {
    @JsonProperty("Forecast")
    Integer forecast;
}
