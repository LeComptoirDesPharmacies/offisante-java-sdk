package fr.lecomptoirdespharmacies.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.entity.Pharmacy;
import lombok.EqualsAndHashCode;
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
