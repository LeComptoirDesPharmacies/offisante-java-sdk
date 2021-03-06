package fr.lecomptoirdespharmacies.offisante.core.manager;

import fr.lecomptoirdespharmacies.offisante.core.api.AuthApi;
import fr.lecomptoirdespharmacies.offisante.entity.http.Token;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.HEADER_AUTHORIZATION;

/**
 * Manage Offisante Token
 */
public class TokenManager {

    private Token token;

    private final AuthApi authApi;

    public TokenManager(@NonNull AuthApi api){
        this.authApi = api;
    }

    /**
     *          Get current token if valid or request for a new token
     * @return  A valid token
     */
    public Token getToken(){
        if(isValid()) return token;
        generateToken();
        return token;
    }

    /**
     *          Check if token is valid
     *          Token should not be null
     *          Token should be change every day
     * @return  Bool
     */
    private boolean isValid(){
        if (token == null) return false;
        if (!token.wasCreatedToday()) return false;
        return true;
    }

    /**
     *           Build authorization header with a valid token
     * @return   Authorization Header
     */
    public Map<String, String> getAuthorizationHeader(){
        Map<String, String> map = new HashMap<String, String>();
        map.put(HEADER_AUTHORIZATION, getToken().getValue());
        return map;
    }

    /**
     * Generate token
     * Could be use to request token manually
     */
    public void generateToken() {
        token = authApi.generateToken();
    }

    /**
     *                  Token is set and manage automatically
     *                  No need to set it instead in test
     * @param token     Token to set
     */
    // ONLY for test purpose !
    public void setToken(Token token) {
        this.token = token;
    }
}
