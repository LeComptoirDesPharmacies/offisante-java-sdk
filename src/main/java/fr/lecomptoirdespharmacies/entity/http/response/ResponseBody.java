package fr.lecomptoirdespharmacies.entity.http.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import fr.lecomptoirdespharmacies.entity.http.Body;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseBody extends Body {

    @JsonProperty("pharmacies")
    private ResponsePharmacy pharmacies;

    @JsonAlias({"overstock","unsold","stock"})
    private List<ResponseResult> results;

    public ResponseBody(Integer code, LocalDateTime next, Integer remaining, String version) {
        super(code, next, remaining, version);
    }
}
