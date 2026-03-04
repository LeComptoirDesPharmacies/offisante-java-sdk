package fr.lecomptoirdespharmacies.offisante.core.manager;

import fr.lecomptoirdespharmacies.offisante.core.api.AuthApi;
import fr.lecomptoirdespharmacies.offisante.entity.http.Token;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.HEADER_AUTHORIZATION;

/**
 * Manage Offisante Token
 * <p>
 * Thread-safe: TokenManager is shared across multiple threads when API calls
 * are made concurrently (e.g. parallel CompletableFuture calls).
 * Without synchronization, concurrent access to the token field causes race conditions
 * where multiple threads generate tokens simultaneously, leading to NullPointerException
 * on the Authorization header (OkHttp rejects null header values).
 */
public class TokenManager {

    // volatile ensures visibility of token changes across threads
    private volatile Token token;

    private final AuthApi authApi;

    public TokenManager(@NonNull AuthApi api) {
        this.authApi = api;
    }

    /**
     * Get current token if valid or request for a new token
     *
     * @return A valid token
     */
    public Token getToken() {
        // Fast path: no lock needed if token is already valid
        if (isValid()) return token;
        synchronized (this) {
            // Double-check after acquiring lock: another thread may have already refreshed the token
            if (isValid()) return token;
            generateToken();
            return token;
        }
    }

    /**
     * Check if token is valid
     * Token should not be null
     * Token should be change every day
     *
     * @return Bool
     */
    private boolean isValid() {
        // Local copy to avoid reading the volatile field twice:
        // another thread could reassign token between the null check and wasCreatedToday()
        Token t = token;
        if (t == null) return false;
        return t.wasCreatedToday();
    }

    /**
     * Build authorization header with a valid token
     *
     * @return Authorization Header
     * @throws IllegalStateException if a valid token could not be obtained
     */
    public Map<String, String> getAuthorizationHeader() {
        Token t = getToken();
        if (t == null) {
            throw new IllegalStateException("Failed to obtain a valid Offisante token");
        }
        Map<String, String> map = new HashMap<>();
        map.put(HEADER_AUTHORIZATION, t.getValue());
        return map;
    }

    /**
     * Generate token
     * Could be use to request token manually
     */
    public synchronized void generateToken() {
        token = authApi.generateToken();
    }

    /**
     * Token is set and manage automatically
     * No need to set it instead in test
     *
     * @param token Token to set
     */
    // ONLY for test purpose !
    public void setToken(Token token) {
        this.token = token;
    }
}
