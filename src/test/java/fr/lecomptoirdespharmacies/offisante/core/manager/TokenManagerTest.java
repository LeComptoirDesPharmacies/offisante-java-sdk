package fr.lecomptoirdespharmacies.offisante.core.manager;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.core.api.AuthApi;
import fr.lecomptoirdespharmacies.offisante.entity.UserCredentials;
import fr.lecomptoirdespharmacies.offisante.entity.http.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.HEADER_AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void test_concurrent_access_should_only_generate_token_once() throws InterruptedException {
        when(authApi.generateToken()).thenAnswer(invocation -> {
            // Simulate slow token generation to increase chance of race condition
            Thread.sleep(50);
            return new Token("concurrent-token", LocalDateTime.now());
        });

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadCount);
        List<Token> results = new CopyOnWriteArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    startLatch.await(); // All threads start at the same time
                    Token token = tokenManager.getToken();
                    results.add(token);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        startLatch.countDown(); // Release all threads simultaneously
        doneLatch.await(5, TimeUnit.SECONDS);
        executor.shutdown();

        // All threads should get a non-null token
        assertEquals(threadCount, results.size());
        results.forEach(token -> {
            assertNotNull(token);
            assertNotNull(token.getValue());
        });

        // generateToken should be called only once thanks to double-checked locking
        verify(authApi, times(1)).generateToken();
    }

    @Test
    void test_concurrent_get_authorization_header_never_returns_null_value() throws InterruptedException {
        when(authApi.generateToken()).thenReturn(validToken);

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadCount);
        List<Map<String, String>> results = new CopyOnWriteArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    startLatch.await();
                    results.add(tokenManager.getAuthorizationHeader());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        startLatch.countDown();
        doneLatch.await(5, TimeUnit.SECONDS);
        executor.shutdown();

        assertEquals(threadCount, results.size());
        results.forEach(map -> {
            assertNotNull(map.get(HEADER_AUTHORIZATION));
            assertEquals(validToken.getValue(), map.get(HEADER_AUTHORIZATION));
        });
    }

@Test
    void test_valid_token_is_reused_without_regeneration() {
        when(authApi.generateToken()).thenReturn(validToken);

        // First call generates the token
        tokenManager.getToken();
        // Second call should reuse it
        Token result = tokenManager.getToken();

        assertEquals(validToken, result);
        verify(authApi, times(1)).generateToken();
    }
}
