package fr.lecomptoirdespharmacies.core.endpoint;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import fr.lecomptoirdespharmacies.entity.http.Body;

public interface Endpoint {

    <T extends Body> T securePost(OffisanteApi api, Uri uri, T body, Class<T> responseCls);

    <T extends Body> T requestToken(OffisanteApi api, Uri uri, UserCredentials body, Class<T> responseCls);
}
