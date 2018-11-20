package fr.lecomptoirdespharmacies.core.json;

import fr.lecomptoirdespharmacies.entity.http.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    private final JsonParser parser = new JsonParser();

    @Test
    void test_token_parsing() {
        final String value = "NDI9sYxsblQw3y70+np0uWwgYCFdfJmH81iL84o7ST4UckGW7YuR7IKWSITDRVsKHlzIeiuUHgqoBeQ21KbdvQ==";

        String json = "{\"code\":0,\"accessToken\":\"NDI9sYxsblQw3y70+np0uWwgYCFdfJmH81iL84o7ST4UckGW7YuR7IKWSITDRVsKHlzIeiuUHgqoBeQ21KbdvQ==\",\"remaining\":10,\"next\":\"Tue, 20 Nov 2018 11:14:18 +0100\",\"version\":\"1.0.4\"}";
        Token t = parser.fromJson(json, Token.class);

        assertEquals(value, t.getValue());
    }
}