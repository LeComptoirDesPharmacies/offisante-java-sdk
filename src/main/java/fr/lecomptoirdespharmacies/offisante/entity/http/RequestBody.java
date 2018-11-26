package fr.lecomptoirdespharmacies.offisante.entity.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.Jsonable;
import fr.lecomptoirdespharmacies.offisante.entity.Pharmacy;
import fr.lecomptoirdespharmacies.offisante.entity.Product;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class RequestBody implements Jsonable {

    @JsonProperty("pharmacies")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final @NonNull List<Pharmacy> pharmacies;

    @JsonProperty("products")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Product> products;
}
