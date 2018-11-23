package fr.lecomptoirdespharmacies.core.json;

import fr.lecomptoirdespharmacies.entity.Pharmacy;
import fr.lecomptoirdespharmacies.entity.Product;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    @Test
    void test_pharmacy_mapping() {
        final Pharmacy p1 = new Pharmacy("cip1234");
        final String expected = "{\"cip\":\"cip1234\"}";
        assertEquals(expected, p1.toJson());
    }

    @Test
    void test_product_mapping() {
        final Product p1 = new Product("product_code");
        final String expected = "{\"code\":\"product_code\"}";
        assertEquals(expected,p1.toJson());
    }

    @Test
    void test_request_body_without_products_mapping() {

        final String expected = "{\"pharmacies\":[{\"cip\":\"pharmacy1\"},{\"cip\":\"pharmacy2\"}]}";

        final Pharmacy p1 = new Pharmacy("pharmacy1");
        final Pharmacy p2 = new Pharmacy("pharmacy2");

        final RequestBody rBody = new CreateRequestBody()
                .withPharmacies(Arrays.asList(p1,p2))
                .build();

        assertEquals(expected, rBody.toJson());
    }

    @Test
    void test_request_body_with_products_mapping() {

        final String expected = "{\"pharmacies\":[{\"cip\":\"pharmacy1\"},{\"cip\":\"pharmacy2\"}]" +
                ",\"products\":[{\"code\":\"123456789\"},{\"code\":\"987654321\"}]}";

        final Pharmacy p1 = new Pharmacy("pharmacy1");
        final Pharmacy p2 = new Pharmacy("pharmacy2");

        final Product pt1 = new Product("123456789");
        final Product pt2 = new Product("987654321");

        final RequestBody rBody = new CreateRequestBody()
                .withPharmacies(Arrays.asList(p1,p2))
                .withProducts(Arrays.asList(pt1,pt2))
                .build();

        assertEquals(expected, rBody.toJson());
    }
}