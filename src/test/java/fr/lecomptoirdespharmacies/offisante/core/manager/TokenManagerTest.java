package fr.lecomptoirdespharmacies.offisante.core.manager;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.core.api.AuthApi;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import fr.lecomptoirdespharmacies.offisante.entity.http.Body;
import fr.lecomptoirdespharmacies.offisante.entity.http.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Map;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.HEADER_AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenManagerTest {

    @Mock
    private OffisanteApi api = new OffisanteApi(
            new UserCredentials("faken","fakep","fakek")
    );

    @Mock
    AuthApi authApi = new AuthApi(api);

    @InjectMocks
    private TokenManager tokenManager;

    private final Token validToken = new Token( "token1", LocalDateTime.now());
    private final Token invalidToken = new Token("token2", LocalDateTime.now().minusDays(1));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_generate_token() {

        when(authApi.generateToken()).thenReturn(validToken);

        assertEquals(tokenManager.getToken(), validToken);
    }

    @Test
    void test_try_to_get_header_with_valid_token() {

        when(authApi.generateToken()).thenReturn(validToken);

        final Map<String,String> map1 = tokenManager.getAuthorizationHeader();

        assertEquals(map1.get(HEADER_AUTHORIZATION), validToken.getValue());
    }

    @Test
    void test_generate_token_when_token_is_invalid() {

        when(authApi.generateToken()).thenReturn(validToken);

        tokenManager.setToken(invalidToken);

        assertEquals(tokenManager.getToken(), validToken);
    }
}