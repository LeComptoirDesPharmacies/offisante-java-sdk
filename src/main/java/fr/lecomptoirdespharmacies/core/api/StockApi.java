package fr.lecomptoirdespharmacies.core.api;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.entity.Pharmacy;
import fr.lecomptoirdespharmacies.entity.Product;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.entity.http.response.stock.StockBody;
import lombok.NonNull;

import java.util.List;

import static fr.lecomptoirdespharmacies.core.Constant.STOCK_API;

/**
 * Stock API permit to retrieve stock of product in pharmacy from Offisante API
 */
public class StockApi extends BaseApi {

    private final CreateUri uriBuilder = new CreateUri().fromUri(STOCK_API);

    public StockApi(OffisanteApi api) {
        super(api);
    }

    /**
     *                      Retrieve stock of products
     * @param pharmacies    Pharmacy where to retrieve stock
     * @return              Request body with stock
     */
    public StockBody getStock(@NonNull List<Pharmacy> pharmacies){

        Uri uri = uriBuilder.build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .build();

        return securePost(getApi(),uri,rBody, StockBody.class);
    }

    /**
     *                      Retrieve stock of products
     * @param pharmacies    Pharmacy where to retrieve stock
     * @param products      Product where to retrieve stock
     * @return              Request body with stock
     */
    public StockBody getStock(@NonNull List<Pharmacy> pharmacies, @NonNull List<Product> products){

        Uri uri = uriBuilder.build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .withProducts(products)
                .build();

        return securePost(getApi(),uri,rBody, StockBody.class);
    }
}
