package fr.lecomptoirdespharmacies.core.json;

import fr.lecomptoirdespharmacies.entity.Pharmacy;
import fr.lecomptoirdespharmacies.entity.Product;
import org.junit.jupiter.api.Test;

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
}