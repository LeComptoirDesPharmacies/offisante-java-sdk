package fr.lecomptoirdespharmacies.offisante;

import fr.lecomptoirdespharmacies.offisante.core.Configuration;
import fr.lecomptoirdespharmacies.offisante.core.api.AuthApi;
import fr.lecomptoirdespharmacies.offisante.core.api.OverstockApi;
import fr.lecomptoirdespharmacies.offisante.core.api.StockApi;
import fr.lecomptoirdespharmacies.offisante.core.api.UnsoldApi;
import fr.lecomptoirdespharmacies.offisante.core.manager.TokenManager;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import lombok.Getter;

/**
 * Main class API
 */
@Getter
public class OffisanteApi {

    public OffisanteApi(UserCredentials credentials) {
        configuration = new Configuration(credentials, false);
        initialise();
    }

    public OffisanteApi(UserCredentials credentials, boolean isProduction) {
        configuration = new Configuration(
                credentials,
                isProduction
        );
        initialise();
    }

    private void initApi(){
        authApi = new AuthApi(this);
        overstockApi = new OverstockApi(this);
        unsoldApi = new UnsoldApi(this);
        stockApi = new StockApi(this);
    }

    private void initialise(){
        initApi();
        tokenManager = new TokenManager(authApi);
    }

    /**
     * Configuration class
     */
    protected final Configuration configuration;

    /** APIs **/
    private AuthApi authApi;
    private OverstockApi overstockApi;
    private UnsoldApi unsoldApi;
    private StockApi stockApi;

    /** Manager **/
    private TokenManager tokenManager;
}
