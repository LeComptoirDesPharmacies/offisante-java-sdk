package fr.lecomptoirdespharmacies.offisante.entity.http.response.overstock;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import lombok.Getter;

import java.util.List;

@Getter
public class OverstockBody extends Body {
    @JsonProperty("results")
    private List<OverstockProduct> results;
}
