package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.Pharmacy;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.ResponseBody;
import lombok.NonNull;

import java.util.List;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.PHARMACY_API;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.START_RETRY;

/**
 * Pharmacy Api permit to check if a drug store has Offisante setup
 */
public class PharmacyApi  extends BaseApi {

    private final CreateUri uriBuilder = new CreateUri().fromUri(PHARMACY_API);

    public PharmacyApi(OffisanteApi api) {
        super(api);
    }

    /**
     *                      Retrieve pharmacies known or unknown by Offisante
     * @param pharmacies    Pharmacies we want to known they state
     * @return              Body with pharmacies
     */
    public ResponseBody getPharmacies(@NonNull List<Pharmacy> pharmacies){

        Uri uri = uriBuilder.build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .build();

        return executePost(uri,rBody, ResponseBody.class, START_RETRY);
    }

}
