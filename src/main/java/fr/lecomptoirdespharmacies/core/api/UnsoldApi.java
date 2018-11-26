package fr.lecomptoirdespharmacies.core.api;

import fr.lecomptoirdespharmacies.OffisanteApi;
import fr.lecomptoirdespharmacies.core.json.deserializer.ResponseResultDeserializer;
import fr.lecomptoirdespharmacies.entity.Pharmacy;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;
import fr.lecomptoirdespharmacies.entity.http.Uri;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateRequestBody;
import fr.lecomptoirdespharmacies.entity.http.builder.CreateUri;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseBody;
import fr.lecomptoirdespharmacies.entity.http.response.ResponseResult;
import fr.lecomptoirdespharmacies.entity.http.response.unsold.UnsoldResponseResult;
import lombok.NonNull;

import java.util.List;

import static fr.lecomptoirdespharmacies.core.Constant.UNSOLD_API;

/**
 * Unsold API permit to get unsold product in pharmacy from offisante API
 */
public class UnsoldApi extends BaseApi {

    private final ResponseResultDeserializer deserializer;

    private final CreateUri uriBuilder = new CreateUri().fromUri(UNSOLD_API);

    public UnsoldApi(OffisanteApi api) {
        super(api);

        // Create deserializer with annotated classes
        deserializer = new ResponseResultDeserializer(api.getReflectionManager()
                .getPropertyRegisterKey(ResponseResult.class, UnsoldResponseResult.class));
    }

    /**
     *                      Retrieve unsold product
     * @param pharmacies    Pharmacies where to retrieve unsold product
     * @return              Response body with unsold
     */
    public ResponseBody getUnsold(@NonNull List<Pharmacy> pharmacies){
        Uri uri = uriBuilder.build();

        RequestBody rBody = new CreateRequestBody()
                .withPharmacies(pharmacies)
                .build();

        return securePost(getApi(),uri,rBody,ResponseBody.class, deserializer);
    }
}
