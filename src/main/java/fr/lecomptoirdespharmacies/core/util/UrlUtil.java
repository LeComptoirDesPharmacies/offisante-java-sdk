package fr.lecomptoirdespharmacies.core.util;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.domain.Uri;
import lombok.NonNull;

public class UrlUtil {

    public String getFullUrl(@NonNull OffisanteApi api, @NonNull Uri uri){
        return api.getConfiguration().getBaseUrl() + uri.toString();
    }
}
