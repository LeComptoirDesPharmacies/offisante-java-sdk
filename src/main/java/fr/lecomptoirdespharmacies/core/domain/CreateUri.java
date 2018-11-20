package fr.lecomptoirdespharmacies.core.domain;

import java.util.HashMap;
import java.util.List;

public class CreateUri {

    private String uri = "";

    /**
     * Contains url optional queries
     * Example : ?key1=value1&key2=value1
     */
    private HashMap<String, List<String>> queries = new HashMap<>();

    public CreateUri fromUri(String uri){
        this.uri = uri;
        return this;
    }

    public CreateUri withQueryParams(HashMap<String, List<String>> queries){
        this.queries = queries;
        return this;
    }

    public Uri build(){
        return new Uri(
                uri,
                queries
        );
    }

}
