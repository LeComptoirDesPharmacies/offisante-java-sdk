package fr.lecomptoirdespharmacies.entity.http;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.joda.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class ResponseBody extends Body {
    public ResponseBody(Integer code, LocalDateTime next, Integer remaining, String version) {
        super(code, next, remaining, version);
    }
}
