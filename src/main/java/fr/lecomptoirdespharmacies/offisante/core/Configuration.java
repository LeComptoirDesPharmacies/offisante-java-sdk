package fr.lecomptoirdespharmacies.offisante.core;

import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import lombok.Getter;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.DEV_BASE_URL;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.PRODUCTION_BASE_URL;


/**
 * API Configuration
 */
@Getter
public class Configuration {

    private final UserCredentials credentials;

    private final boolean isProd;

    /**
     *                      Store user credentials and runtime environment
     * @param credentials   User credentials given by Offisante
     * @param isProd        Runtime environment Prod ( True ) or Dev (False)
     */
    public Configuration(UserCredentials credentials, boolean isProd) {
        this.credentials = credentials;
        this.isProd = isProd;
    }

    /**
     *          Get dev or prod URL depends on runtime environment
     * @return  Url string
     */
    public String getBaseUrl() {
        return isProd ? PRODUCTION_BASE_URL : DEV_BASE_URL;
    }
}
