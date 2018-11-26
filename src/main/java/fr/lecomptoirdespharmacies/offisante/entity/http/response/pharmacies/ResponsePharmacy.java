package fr.lecomptoirdespharmacies.offisante.entity.http.response.pharmacies;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.Pharmacy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponsePharmacy {

    @JsonProperty("known")
    private List<Pharmacy> known;

    @JsonProperty("unknown")
    private List<Pharmacy> unknown;
}
