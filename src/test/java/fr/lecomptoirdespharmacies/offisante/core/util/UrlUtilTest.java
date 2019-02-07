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

    @Test
    void test_full_url_creation_for_dev_with_one_param() {
        final String expected = DEV_BASE_URL+"/uri?q1=0123456789";

        Uri uri = new CreateUri()
                .fromUri("/uri")
                .addQueryParams("q1","0123456789")
                .build();

        assertEquals(expected, util.getFullUrl(devApi, uri));
    }

    @Test
    void test_full_url_creation_for_prod_with_params() {
        final String expected = PRODUCTION_BASE_URL+"/uri?q1=0123456789&q2=0987654321";

        Uri uri = new CreateUri()
                .fromUri("/uri")
                .addQueryParams("q1","0123456789")
                .addQueryParams("q2","0987654321")
                .build();

        assertEquals(expected, util.getFullUrl(prodApi, uri));
    }
}