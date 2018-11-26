package fr.lecomptoirdespharmacies.core.api;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.entity.Pharmacy;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import fr.lecomptoirdespharmacies.entity.http.response.overstock.OverstockBody;
import lombok.NonNull;

import java.util.List;

import static fr.lecomptoirdespharmacies.core.Constant.OVERSTOCK_API;

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

        return securePost(getApi(),uri,rBody, OverstockBody.class);
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

        return securePost(getApi(),uri,rBody, OverstockBody.class);
    }

}
