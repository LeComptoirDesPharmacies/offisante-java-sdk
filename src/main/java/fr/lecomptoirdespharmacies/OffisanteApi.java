package fr.lecomptoirdespharmacies;

import fr.lecomptoirdespharmacies.core.Configuration;
import fr.lecomptoirdespharmacies.core.api.AuthApi;
import fr.lecomptoirdespharmacies.core.api.OverstockApi;
import fr.lecomptoirdespharmacies.core.api.StockApi;
import fr.lecomptoirdespharmacies.core.api.UnsoldApi;
import fr.lecomptoirdespharmacies.core.manager.ReflectionManager;
import fr.lecomptoirdespharmacies.core.manager.TokenManager;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import lombok.Getter;

import static fr.lecomptoirdespharmacies.core.Constant.ENTITY_PACKAGE_NAME;

//TODO: Logging module ??
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
    private ReflectionManager reflectionManager = new ReflectionManager(ENTITY_PACKAGE_NAME);


}
