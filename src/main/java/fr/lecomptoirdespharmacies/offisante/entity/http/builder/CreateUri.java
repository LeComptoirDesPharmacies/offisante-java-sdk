package fr.lecomptoirdespharmacies.offisante.entity.http.builder;

import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;

import java.util.HashMap;

public class CreateUri {

    private String uri = "";

    /**
     * Contains url optional queries
     * Example : ?key1=value1&key2=value1
     */
    private HashMap<String, String> queries = new HashMap<>();

    public CreateUri fromUri(String uri){
        this.uri = uri;
        return this;
    }

    public CreateUri addQueryParams(String key, String value){
        queries.putIfAbsent(key, value);
        return this;
    }

    public Uri build(){
        return new Uri(
                uri,
                queries
        );
    }

}
