package com.example.onlineshop.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MainResponse {

    @SerializedName("products")
    private List<ProductsItem> products;

    public List<ProductsItem> getProducts(){
        return products;
    }
}
