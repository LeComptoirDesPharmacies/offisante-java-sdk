package fr.lecomptoirdespharmacies.offisante.core.api;

import fr.lecomptoirdespharmacies.offisante.OffisanteApi;
import fr.lecomptoirdespharmacies.offisante.entity.Pharmacy;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.offisante.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.offisante.entity.http.Uri;
import fr.lecomptoirdespharmacies.offisante.entity.http.response.overstock.OverstockBody;
import lombok.NonNull;

import java.util.List;

import static fr.lecomptoirdespharmacies.offisante.core.Constant.OVERSTOCK_API;
import static fr.lecomptoirdespharmacies.offisante.core.Constant.START_RETRY;

/**
 * Overstock API permit to get overstock product in drugs stores from Offisante
 */
public class OverstockApi extends BaseApi {

    private final CreateUri uriBuilder = new CreateUri().fromUri(OVERSTOCK_API);

    public OverstockApi(OffisanteApi api) {
        super(api);
    }

    /**
     *                      Get overstock for a list of pharmacy
     *                      default month number is 12
     * @param pharmacies    Pharmacies to retrieve stock
     * @return              Response body with overstock
     */
    public OverstockBody getOverstock(@NonNull List<Pharmacy> pharmacies){

        Uri uri = uriBuilder.build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .build();

        return executePost(uri,rBody, OverstockBody.class, START_RETRY);
    }

    /**
     *                      Permit to change number of overstock month
     * @param pharmacies    Pharmacies to retrieve stock
     * @param month         Custom number of month
     * @return              Response body with overstock
     */
    public OverstockBody getOverstock(@NonNull List<Pharmacy> pharmacies, int month){

        Uri uri = uriBuilder.addQueryParams("months",Integer.toString(month)).build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .build();

        return executePost(uri,rBody, OverstockBody.class, START_RETRY);
    }

}
