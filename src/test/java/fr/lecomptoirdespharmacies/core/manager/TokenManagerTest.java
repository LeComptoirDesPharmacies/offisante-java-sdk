package fr.lecomptoirdespharmacies.core.manager;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.api.AuthApi;
import fr.lecomptoirdespharmacies.entity.UserCredentials;
import fr.lecomptoirdespharmacies.entity.http.Body;
import fr.lecomptoirdespharmacies.entity.http.Token;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Map;
import static fr.lecomptoirdespharmacies.core.Constant.HEADER_AUTHORIZATION;
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

    private final Token validToken = new Token(0, LocalDateTime.now(), 10, "1.0.0", "token1");
    private final Token invalidToken = new Token(0, LocalDateTime.now(), 0, "1.0.0", "token2");

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

    @Test
    void test_update_token_remaining() {

        final Integer remaining = 5;

        final Body bodyMock = mock(Body.class);

        when(bodyMock.getRemaining())
                .thenReturn(remaining);

        tokenManager.setToken(validToken);

        tokenManager.updateRemaining(bodyMock);

        assertEquals(remaining, tokenManager.getToken().getRemaining());
        assertEquals(validToken.getValue(), tokenManager.getToken().getValue());
    }

    @Test
    void test_update_token_remaining_with_null_remaining() {

        final Body bodyMock = mock(Body.class);

        when(bodyMock.getRemaining())
                .thenReturn(null);

        tokenManager.setToken(validToken);

        tokenManager.updateRemaining(bodyMock);

        assertEquals(validToken.getRemaining(), tokenManager.getToken().getRemaining());
        assertEquals(validToken.getValue(), tokenManager.getToken().getValue());
    }

}