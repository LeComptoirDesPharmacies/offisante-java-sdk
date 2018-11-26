package fr.lecomptoirdespharmacies.offisante.core.util;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import org.junit.jupiter.api.Test;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.DEV_BASE_URL;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.PRODUCTION_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlUtilTest {

    private OffisanteApi devApi = new OffisanteApi(
            new UserCredentials("faken","fakep","fakek")
    );

    private OffisanteApi prodApi = new OffisanteApi(
            new UserCredentials("faken","fakep","fakek"),
            true
    );

    private final UrlUtil util = new UrlUtil();

    @Test
    void test_full_url_creation_for_dev() {
        final String expected = DEV_BASE_URL+"/uri";

        Uri uri = new CreateUri()
                .fromUri("/uri")
                .build();

        assertEquals(expected, util.getFullUrl(devApi, uri));
    }

    @Test
    void test_full_url_creation_for_prod() {
        final String expected = PRODUCTION_BASE_URL+"/uri";

        Uri uri = new CreateUri()
                .fromUri("/uri")
                .build();

        assertEquals(expected, util.getFullUrl(prodApi, uri));
    }
}