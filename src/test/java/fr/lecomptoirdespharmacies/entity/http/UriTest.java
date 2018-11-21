package fr.lecomptoirdespharmacies.entity.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UriTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void test_uri_to_string_with_queries() {
        final String expected = "/uri?key1=value1&key2=value2";

        String strUri = "/uri";
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Uri uri = new Uri(strUri,map);

        assertEquals(expected,uri.toString());
    }

    @Test
    void test_uri_to_string_without_queries() {
        final String expected = "/uri";

        String strUri = "/uri";
        HashMap<String, String> map = new HashMap<>();

        Uri uri = new Uri(strUri,map);

        assertEquals(expected,uri.toString());
    }
}