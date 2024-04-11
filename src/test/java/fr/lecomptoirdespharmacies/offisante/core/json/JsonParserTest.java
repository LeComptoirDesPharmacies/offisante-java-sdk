package fr.lecomptoirdespharmacies.offisante.core.json;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import fr.lecomptoirdespharmacies.offisante.entity.http.Token;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.overstock.OverstockBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.stock.StockBody;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    private OffisanteApi api = new OffisanteApi(
            new UserCredentials("faken","fakep","fakek")
    );

    @Test
    void test_token_parsing() {
        final String value = "NDI9sYxsblQw3y70+np0uWwgYCFdfJmH81iL84o7TOKENuR7IKWSITDRVsKHlzIeiuUHgqoBeQ21KbdvQ==";

        String json = "{\"code\":0,\"accessToken\":\"NDI9sYxsblQw3y70+np0uWwgYCFdfJmH81iL84o7TOKENuR7IKWSITDRVsKHlzIeiuUHgqoBeQ21KbdvQ==\",\"remaining\":10,\"next\":\"Tue, 20 Nov 2018 11:14:18 +0100\",\"version\":\"1.0.4\"}";

        JsonParser parser = new CreateJsonParser(json).build();
        Token t = parser.parseJsonTo(Token.class);

        assertEquals(value, t.getValue());
    }

    @Test
    void test_overstock_response_parsing() {
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File jsonFile = new File(classLoader.getResource("json/fake_overstock.json").getFile());
            String content = new String (Files.readAllBytes(jsonFile.toPath()), Charset.forName("UTF-8"));

            JsonParser parser = new CreateJsonParser(content).build();

            OverstockBody response = parser.parseJsonTo(OverstockBody.class);

            assertEquals(1,response.getOverstock().size());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void test_stock_response_parsing() {
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File jsonFile = new File(classLoader.getResource("json/fake_stock.json").getFile());
            String content = new String (Files.readAllBytes(jsonFile.toPath()), Charset.forName("UTF-8"));

            JsonParser parser = new CreateJsonParser(content).build();

            StockBody response = parser.parseJsonTo(StockBody.class);

            assertEquals(1,response.getStock().size());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}