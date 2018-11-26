package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.Pharmacy;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.unsold.UnsoldBody;
import lombok.NonNull;

import java.util.List;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.UNSOLD_API;

/**
 * Unsold API permit to get unsold product in pharmacy from offisante API
 */
public class UnsoldApi extends BaseApi {

    private final CreateUri uriBuilder = new CreateUri().fromUri(UNSOLD_API);

    public UnsoldApi(OffisanteApi api) {
        super(api);
    }

    /**
     *                      Retrieve unsold product
     * @param pharmacies    Pharmacies where to retrieve unsold product
     * @return              Response body with unsold
     */
    public UnsoldBody getUnsold(@NonNull List<Pharmacy> pharmacies){
        Uri uri = uriBuilder.build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .build();

        return securePost(getApi(),uri,rBody, UnsoldBody.class);
    }
}
