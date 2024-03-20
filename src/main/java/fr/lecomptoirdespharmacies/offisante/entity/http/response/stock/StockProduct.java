package fr.lecomptoirdespharmacies.offisante.entity.http.response.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.Cip13Deserializer;
import fr.lecomptoirdespharmacies.offisante.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockProduct extends BaseEntity {
    @JsonProperty("id_product")
    Long id;

    @JsonProperty("cip7")
    String cip7;

    @JsonDeserialize(using = Cip13Deserializer.class)
    @JsonProperty("code13")
    List<String> cip13;

    @JsonProperty("label")
    String label;

    @JsonProperty("qty")
    Integer quantity;

    @JsonProperty("unit_price")
    BigDecimal unitPrice;

    @JsonProperty("time")
    Long time;

    @JsonProperty("lastVATRate")
    BigDecimal lastVATRate;

    @JsonIgnore
    public List<String> getCodes(){
        List<String> codes = new ArrayList<>();
        if(getCip7() != null){
            codes.add(getCip7());
        }
        if(getCip13() != null){
            codes.addAll(getCip13());
        }
        return codes;
    }
}
