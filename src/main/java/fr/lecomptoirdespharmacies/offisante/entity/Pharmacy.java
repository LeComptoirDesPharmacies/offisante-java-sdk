package fr.lecomptoirdespharmacies.offisante.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.StockDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pharmacy extends BaseEntity {
    @JsonProperty("cip")
    @NonNull
    String cip;

    @JsonProperty("last_date_stock")
    @JsonDeserialize(using = StockDateTimeDeserializer.class)
    LocalDateTime lastDateStock;
}
