package fr.lecomptoirdespharmacies.core.endpoint;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.domain.Uri;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import fr.lecomptoirdespharmacies.entity.http.Body;
import fr.lecomptoirdespharmacies.entity.http.Jsonable;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface Endpoint {

    <T extends Body> T securePost(OffisanteApi api, Uri uri, T body, Class<T> responseCls);

    <T extends Body> T requestToken(OffisanteApi api, Uri uri, UserCredentials body, Class<T> responseCls);
}
