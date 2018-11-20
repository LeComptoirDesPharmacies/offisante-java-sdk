package fr.lecomptoirdespharmacies.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Pharmacie extends BaseEntity {
    @JsonProperty("cip")
    String cip;
}
