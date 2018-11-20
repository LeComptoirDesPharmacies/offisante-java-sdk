package fr.lecomptoirdespharmacies.core.manager;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.api.AuthApi;
import fr.lecomptoirdespharmacies.entity.http.Token;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    private Token token;

    private final AuthApi authApi;

    public TokenManager(@NonNull OffisanteApi api) {
        this.authApi = new AuthApi(api);
    }

    private Token getToken(){
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
        map.put("Authorization",getToken().getValue());
        return map;
    }

}
