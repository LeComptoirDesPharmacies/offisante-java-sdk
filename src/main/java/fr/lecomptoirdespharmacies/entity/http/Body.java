package fr.lecomptoirdespharmacies.entity.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.lecomptoirdespharmacies.core.json.deserializer.LocalDateTimeDeserializer;
import fr.lecomptoirdespharmacies.entity.Jsonable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Body implements Jsonable {

    @JsonProperty("code")
    Integer code;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("next")
    LocalDateTime next;

    @JsonProperty("remaining")
    Integer remaining;

    @JsonProperty("version")
    String version;

}
