package fr.lecomptoirdespharmacies.offisante.entity.http;

import lombok.NonNull;
import lombok.Value;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.stream.Collector;

@Value
public class Uri {

    private final @NonNull String uri;

    private final HashMap<String, String> queries;

    // get all queries and parse it to "?key1=value1&key2=value2..."
    @Override
    public String toString() {
        return uri + queries.entrySet()
                .stream()
                .map(entry -> entry.getKey()+"="+entry.getValue())
                .collect(Collector.of(
                        () -> new StringJoiner("&", "?", "").setEmptyValue(""),
                        StringJoiner::add,
                        StringJoiner::merge,
                        StringJoiner::toString));
    }
}
