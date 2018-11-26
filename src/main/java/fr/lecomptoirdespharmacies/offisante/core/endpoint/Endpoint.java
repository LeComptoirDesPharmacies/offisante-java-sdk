package fr.lecomptoirdespharmacies.offisante.core.endpoint;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import lombok.NonNull;

public interface Endpoint {

    /**
     *                      Use Secure post to add Token in request
     * @param api           Offisante APi
     * @param uri           Uri with parameters
     * @param body          Content of the request
     * @param responseCls   Class to parse json in
     * @param <T>           Class that extend body
     * @return              Response casted in response class
     */
    <T extends Body> T securePost(@NonNull OffisanteApi api, @NonNull Uri uri,
                                  @NonNull RequestBody body, @NonNull Class<T> responseCls);

    /**
     *                      Request token from Offisante API
     * @param api           Offisante APi
     * @param uri           Uri with parameters
     * @param body          Content of the request
     * @param responseCls   Class to parse json in
     * @param <T>           Class that extend body
     * @return              Response casted in response class
     */
    <T extends Body> T requestToken(@NonNull OffisanteApi api, @NonNull Uri uri,
                                    @NonNull UserCredentials body, @NonNull Class<T> responseCls);
}
