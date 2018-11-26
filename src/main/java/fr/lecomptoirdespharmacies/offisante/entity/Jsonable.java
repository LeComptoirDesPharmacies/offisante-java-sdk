package fr.lecomptoirdespharmacies.offisante.entity;

import fr.lecomptoirdespharmacies.offisante.core.json.JsonMapper;

public interface Jsonable {
    default String toJson(){
        JsonMapper parser = new JsonMapper();
        return parser.toJson(this);
    }
}
