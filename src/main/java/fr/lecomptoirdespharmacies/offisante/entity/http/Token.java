package fr.lecomptoirdespharmacies.offisante.entity.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Token extends Body {
    @JsonProperty("accessToken")
    @NonNull String value;

    public Token(Integer code, LocalDateTime next, Integer remaining, String version, @NonNull String value) {
        super(code, next, remaining, version);
        this.value = value;
    }

}
