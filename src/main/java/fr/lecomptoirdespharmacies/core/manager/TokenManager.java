package fr.lecomptoirdespharmacies.core.manager;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.api.AuthApi;
import fr.lecomptoirdespharmacies.entity.http.Token;
import lombok.NonNull;
import java.util.HashMap;
import java.util.Map;

import static fr.lecomptoirdespharmacies.core.Constant.HEADER_AUTHORIZATION;

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
        token = authApi.generateToken();
        return token;
    }

    /**
     *          Check if token is valid
     * @return  Bool
     */
    private boolean isValid(){
        if (token == null) return false;
        if (token.getRemaining() <= 0) return false;
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
     *                      Update remaining use of the token and generate a new one with
     *                      current remaining
     * @param remaining     Current remaining
     */
    public void updateRemaining(int remaining){
        token = new Token(
                token.getCode(),
                token.getNext(),
                remaining,
                token.getVersion(),
                token.getValue()
        );
    }

    /**
     *                  Token is set and change automatically
     *                  No need to set it instead in test
     * @param token     Token to set
     */
    // ONLY for test purpose !
    public void setToken(Token token) {
        this.token = token;
    }
}
