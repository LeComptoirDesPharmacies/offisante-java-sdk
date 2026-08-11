package fr.lecomptoirdespharmacies.offisante.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayloadUtilTest {

    @Test
    void json_object_is_a_json_payload() {
        assertTrue(PayloadUtil.isJsonPayload("{\"code\":0}"));
    }

    @Test
    void json_array_with_leading_blanks_is_a_json_payload() {
        assertTrue(PayloadUtil.isJsonPayload("\n  [{\"cip\":\"2163366\"}]"));
    }

    @Test
    void html_error_page_is_not_a_json_payload() {
        assertFalse(PayloadUtil.isJsonPayload("<!DOCTYPE html><html><body>502 Bad Gateway</body></html>"));
    }

    @Test
    void empty_and_null_payloads_are_not_json_payloads() {
        assertFalse(PayloadUtil.isJsonPayload(""));
        assertFalse(PayloadUtil.isJsonPayload("   \n "));
        assertFalse(PayloadUtil.isJsonPayload(null));
    }

    @Test
    void excerpt_flattens_the_payload_on_a_single_line() {
        assertEquals("<html> <body>502</body> </html>",
                PayloadUtil.excerpt("<html>\n  <body>502</body>\n</html>"));
    }

    @Test
    void excerpt_truncates_a_long_payload() {
        final String payload = "x".repeat(PayloadUtil.EXCERPT_MAX_LENGTH + 50);

        final String excerpt = PayloadUtil.excerpt(payload);

        assertTrue(excerpt.startsWith("x".repeat(PayloadUtil.EXCERPT_MAX_LENGTH)));
        assertTrue(excerpt.contains("truncated"));
        assertTrue(excerpt.contains(String.valueOf(payload.length())));
    }

    @Test
    void excerpt_describes_missing_and_empty_payloads() {
        assertEquals("<null>", PayloadUtil.excerpt(null));
        assertEquals("<empty>", PayloadUtil.excerpt("   "));
    }
}
