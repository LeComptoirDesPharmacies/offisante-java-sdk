package fr.lecomptoirdespharmacies.core.util;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import lombok.NonNull;

/**
 * Class to construct String URL
 */
public class UrlUtil {

    public String getFullUrl(@NonNull OffisanteApi api, @NonNull Uri uri){
        return api.getConfiguration().getBaseUrl() + uri.toString();
    }
}
