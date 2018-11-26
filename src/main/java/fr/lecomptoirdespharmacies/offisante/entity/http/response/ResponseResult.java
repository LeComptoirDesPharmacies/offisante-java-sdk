package fr.lecomptoirdespharmacies.offisante.entity.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ResponseResult {
    @JsonProperty("cip")
    private String cipPharmacy;
}
