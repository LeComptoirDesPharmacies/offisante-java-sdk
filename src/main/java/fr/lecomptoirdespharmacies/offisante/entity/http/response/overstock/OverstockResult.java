package fr.lecomptoirdespharmacies.offisante.entity.http.response.overstock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.Cip13Deserializer;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.ProductDateTimeDeserializer;
import fr.lecomptoirdespharmacies.offisante.entity.BaseEntity;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverstockResult extends BaseEntity {
    @JsonProperty("ID_PRODUIT")
    Long id;

    @JsonProperty("CODES7")
    String cip7;

    @JsonDeserialize(using = Cip13Deserializer.class)
    @JsonProperty("CODES13")
    List<String> cip13;

    @JsonProperty("LIB_PRODUIT")
    String label;

    @JsonProperty("QTE")
    Integer quantity;

    @JsonProperty("PRIX_VENTE_TTC")
    BigDecimal unitPrice;

    @JsonProperty("DATE_MODIFICATION")
    @JsonDeserialize(using = ProductDateTimeDeserializer.class)
    LocalDateTime time;

    @JsonProperty("FORECAST")
    Integer forecast;

    @JsonProperty("UNSOLD")
    Integer unsold;

    @JsonProperty("LAST_SALE")
    @JsonDeserialize(using = ProductDateTimeDeserializer.class)
    LocalDate lastSale;

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
