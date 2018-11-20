package fr.lecomptoirdespharmacies.entity.http;

import fr.lecomptoirdespharmacies.core.json.JsonMapper;

public interface Jsonable {
    default String toJson(){
        JsonMapper parser = new JsonMapper();
        return parser.toJson(this);
    }
}
