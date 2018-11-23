package fr.lecomptoirdespharmacies.entity.http.builder;

import fr.lecomptoirdespharmacies.entity.http.Uri;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUriTest {
    @Test
    void test_uri_creation_without_queries() {
        final String uriExpected = "/uri/1";
        Uri uri = new CreateUri().fromUri(uriExpected).build();

        assertEquals(uriExpected, uri.toString());
    }

    @Test
    void test_uri_creation_with_queries() {
        final String uriExpected = "/uri/1?key1=value1&key2=value2";
        Uri uri = new CreateUri()
                .fromUri("/uri/1")
                .addQueryParams("key1","value1")
                .addQueryParams("key1","value5")
                .addQueryParams("key2","value2")
                .build();

        assertEquals(uriExpected, uri.toString());
    }
}