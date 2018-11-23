package fr.lecomptoirdespharmacies.entity.http.builder;

import fr.lecomptoirdespharmacies.entity.Pharmacy;
import fr.lecomptoirdespharmacies.entity.Product;
import fr.lecomptoirdespharmacies.entity.http.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class CreateRequestBody {

    private List<Pharmacy> pharmacies = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    public CreateRequestBody fromScratch(List<Pharmacy> pharmacies, List<Product> products){
        this.pharmacies = pharmacies;
        this.products = products;
        return this;
    }

    public CreateRequestBody withPharmacies(List<Pharmacy> pharmacies){
        this.pharmacies = pharmacies;
        return this;
    }

    public CreateRequestBody withProducts(List<Product> products){
        this.products = products;
        return this;
    }

    public RequestBody build(){
        return new RequestBody(
                pharmacies,
                products
        );
    }
}
