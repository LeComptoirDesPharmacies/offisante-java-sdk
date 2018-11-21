package fr.lecomptoirdespharmacies;

import fr.lecomptoirdespharmacies.core.Configuration;
import fr.lecomptoirdespharmacies.core.api.AuthApi;
import fr.lecomptoirdespharmacies.core.manager.TokenManager;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import lombok.Getter;

/**
 * Main class API
 */
@Getter
public class OffisanteApi {

    public OffisanteApi(UserCredentials credentials) {
        configuration = new Configuration(credentials, false);
        authApi = new AuthApi(this);
        tokenManager = new TokenManager(authApi);
    }

    public OffisanteApi(UserCredentials credentials, boolean isProduction) {
        configuration = new Configuration(
                credentials,
                isProduction
        );
        authApi = new AuthApi(this);
        tokenManager = new TokenManager(authApi);
    }

    /**
     * Configuration class
     */
    protected Configuration configuration;

    /** APIs **/
    private AuthApi authApi;

    /** Manager **/
    private TokenManager tokenManager;

}
