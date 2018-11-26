package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.entity.http.Token;
import lombok.NonNull;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.AUTH_URI;

/**
 * This API permit to has a token from OffisanteApi
 */
public class AuthApi extends BaseApi {

    public AuthApi(@NonNull OffisanteApi api) {
        super(api);
    }

    /**
     *          Retrieve token from Offisante Api
     * @return  Return token retrieved
     */
    public Token generateToken(){
        Uri uri = new CreateUri().fromUri(AUTH_URI).build();
        return requestToken(getApi(), uri, getApi().getConfiguration().getCredentials(), Token.class);
    }
}
