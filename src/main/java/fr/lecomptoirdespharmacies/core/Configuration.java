package fr.lecomptoirdespharmacies.core;

import fr.lecomptoirdespharmacies.entity.UserCredentials;
import lombok.Getter;
import static fr.lecomptoirdespharmacies.core.Constant.DEV_BASE_URL;
import static fr.lecomptoirdespharmacies.core.Constant.PRODUCTION_BASE_URL;


@Getter
public class Configuration {

    private final UserCredentials credentials;

    private final boolean isProd;

    public Configuration(UserCredentials credentials, boolean isProd) {
        this.credentials = credentials;
        this.isProd = isProd;
    }

    public String getBaseUrl() {
        return isProd ? PRODUCTION_BASE_URL : DEV_BASE_URL;
    }
}
