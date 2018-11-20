package fr.lecomptoirdespharmacies.core.api;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.endpoint.PostEndpoint;
import lombok.Getter;

@Getter
abstract class BaseApi extends PostEndpoint {

    private OffisanteApi api;

    protected BaseApi(OffisanteApi api) {
        this.api = api;
    }
}
