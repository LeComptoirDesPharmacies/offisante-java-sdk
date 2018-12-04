package fr.lecomptoirdespharmacies.offisante;

import fr.lecomptoirdespharmacies.offisante.core.Configuration;
import fr.lecomptoirdespharmacies.offisante.core.api.*;
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
        pharmacyApi = new PharmacyApi(this);
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
    private PharmacyApi pharmacyApi;

    /** Manager **/
    private TokenManager tokenManager;
}
