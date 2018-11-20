package fr.lecomptoirdespharmacies.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Value
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
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
    DateTime lastSale;

}
