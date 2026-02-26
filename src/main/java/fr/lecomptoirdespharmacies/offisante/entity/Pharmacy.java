package fr.lecomptoirdespharmacies.offisante.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.LocalDateDeserializer;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pharmacy extends BaseEntity {
    @JsonProperty("cip")
    @NonNull
    String cip;

    @JsonProperty("last_date_stock")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate lastDateStock;
}
