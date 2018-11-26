package fr.lecomptoirdespharmacies.offisante.entity.http.response.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.ResponseBody;
import lombok.Getter;

import java.util.List;

@Getter
public class StockBody extends ResponseBody {
    @JsonProperty("stock")
    private List<StockResult> stock;
}
