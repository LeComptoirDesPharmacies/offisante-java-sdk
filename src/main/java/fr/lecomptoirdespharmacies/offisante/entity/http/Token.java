package fr.lecomptoirdespharmacies.offisante.entity.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Token extends Body {
    @JsonProperty("accessToken")
    @NonNull String value;

    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    public boolean wasCreatedToday(){
        return createdAt.getDayOfYear() == LocalDateTime.now().getDayOfYear();
    }

}
