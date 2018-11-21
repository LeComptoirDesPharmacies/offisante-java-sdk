package fr.lecomptoirdespharmacies.core.api;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.endpoint.PostEndpoint;
import fr.lecomptoirdespharmacies.entity.http.Body;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import lombok.Getter;

@Getter
abstract class BaseApi extends PostEndpoint {

    private OffisanteApi api;

    protected BaseApi(OffisanteApi api) {
        this.api = api;
    }

    @Override
    public <T extends Body> T securePost(OffisanteApi api, Uri uri, T body, Class<T> responseCls) {
        T response = super.securePost(api, uri, body, responseCls);
        api.getTokenManager().updateRemaining(response.getRemaining());
        return response;
    }
}
