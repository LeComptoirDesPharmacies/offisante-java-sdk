package fr.lecomptoirdespharmacies.offisante.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.pharmacies.ResponsePharmacy;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class ResponseBody extends Body {
    @JsonProperty("pharmacies")
    private ResponsePharmacy pharmacies;
}
