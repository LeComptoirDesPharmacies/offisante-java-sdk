package fr.lecomptoirdespharmacies.offisante.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.offisante.core.json.deserializer.ProductDateTimeDeserializer;
import lombok.*;
import org.joda.time.LocalDateTime;

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
    @JsonDeserialize(using = ProductDateTimeDeserializer.class)
    LocalDateTime lastDateStock;
}
