package fr.lecomptoirdespharmacies.core.domain;

import lombok.Value;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Value
public class Uri {

    private final String uri;

    private final HashMap<String, List<String>> queries;

    @Override
    public String toString() {
        return uri + queries.entrySet()
                .stream()
                .map(entry -> {
                            return entry.getValue()
                                    .stream().map(value ->
                                            "&"+entry.getKey()+"="+value
                                    ).collect(joining());
                        }
                )
                .collect(joining());
    }
}
