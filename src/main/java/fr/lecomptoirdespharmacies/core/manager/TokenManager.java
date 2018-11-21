package fr.lecomptoirdespharmacies.core.manager;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.api.AuthApi;
import fr.lecomptoirdespharmacies.entity.http.Token;
import lombok.NonNull;
import java.util.HashMap;
import java.util.Map;

import static fr.lecomptoirdespharmacies.core.Constant.HEADER_AUTHORIZATION;

public class TokenManager {

    private Token token;

    private final AuthApi authApi;

    public TokenManager(@NonNull AuthApi api){
        this.authApi = api;
    }

    public Token getToken(){
        if(isValid()) return token;
        token = authApi.generateToken();
        return token;
    }

    private boolean isValid(){
        if (token == null) return false;
        if (token.getRemaining() <= 0) return false;
        return true;
    }

    public Map<String, String> getAuthorizationHeader(){
        Map<String, String> map = new HashMap<String, String>();
        map.put(HEADER_AUTHORIZATION, getToken().getValue());
        return map;
    }

    public void updateRemaining(int remaining){
        token = new Token(
                token.getCode(),
                token.getNext(),
                remaining,
                token.getVersion(),
                token.getValue()
        );
    }

    // ONLY for test purpose !
    public void setToken(Token token) {
        this.token = token;
    }
}
