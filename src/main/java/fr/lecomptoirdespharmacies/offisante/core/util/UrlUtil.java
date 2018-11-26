package fr.lecomptoirdespharmacies.offisante.core.util;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import lombok.NonNull;

/**
 * Class to construct String URL
 */
public class UrlUtil {

    public String getFullUrl(@NonNull OffisanteApi api, @NonNull Uri uri){
        return api.getConfiguration().getBaseUrl() + uri.toString();
    }
}
