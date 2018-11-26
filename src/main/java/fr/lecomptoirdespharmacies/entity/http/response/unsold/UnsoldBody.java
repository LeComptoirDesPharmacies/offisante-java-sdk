package fr.lecomptoirdespharmacies.entity.http.response.unsold;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseBody;
import lombok.Getter;

import java.util.List;

@Getter
public class UnsoldBody extends ResponseBody {
    @JsonProperty("unsold")
    private List<UnsoldResult> unsold;
}
