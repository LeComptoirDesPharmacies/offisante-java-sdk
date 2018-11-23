package fr.lecomptoirdespharmacies.entity.http.response.overstock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.lecomptoirdespharmacies.core.json.deserializer.PropertyRegister;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)

@PropertyRegister(key = "products")
public class OverstockResponseResult extends ResponseResult {
    @JsonProperty("products")
    private List<OverstockProduct> products;
}
