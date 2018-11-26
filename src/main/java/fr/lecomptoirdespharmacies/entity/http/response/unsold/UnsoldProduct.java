package fr.lecomptoirdespharmacies.entity.http.response.unsold;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.core.json.deserializer.ProductDateTimeDeserializer;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseProduct;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsoldProduct extends ResponseProduct {
    @JsonProperty("Last_Sale")
    @JsonDeserialize(using = ProductDateTimeDeserializer.class)
    LocalDateTime lastSale;
}
