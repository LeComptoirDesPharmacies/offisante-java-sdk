package fr.lecomptoirdespharmacies.entity.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Token extends Body {
    @JsonProperty("accessToken")
    String value;
}
