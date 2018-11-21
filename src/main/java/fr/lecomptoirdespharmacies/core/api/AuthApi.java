package fr.lecomptoirdespharmacies.core.api;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.entity.http.Builder.CreateUri;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import fr.lecomptoirdespharmacies.entity.http.Token;
import lombok.NonNull;

import static fr.lecomptoirdespharmacies.core.Constant.AUTH_URI;

public class AuthApi extends BaseApi {

    public AuthApi(@NonNull OffisanteApi api) {
        super(api);
    }

    public Token generateToken(){
        Uri uri = new CreateUri().fromUri(AUTH_URI).build();
        return requestToken(getApi(), uri, getApi().getConfiguration().getCredentials(), Token.class);
    }
}
