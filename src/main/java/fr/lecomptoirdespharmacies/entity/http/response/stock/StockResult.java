package fr.lecomptoirdespharmacies.entity.http.response.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static fr.lecomptoirdespharmacies.core.Constant.JSON_PRODUCTS_KEY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)

public class StockResult extends ResponseResult {
    @JsonProperty(JSON_PRODUCTS_KEY)
    private List<StockProduct> products;
}
