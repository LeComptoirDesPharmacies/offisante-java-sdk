package fr.lecomptoirdespharmacies.offisante.entity.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
