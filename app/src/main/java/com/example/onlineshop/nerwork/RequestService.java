package com.example.onlineshop.nerwork;


import com.example.onlineshop.model.MainResponse;
import com.example.onlineshop.model.ProductsItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RequestService {

    @GET("products/")
    Call<List<ProductsItem>> getProducts(@QueryMap Map<String, String> map);

    @GET("products/{id}/")
    Call<ProductsItem> getProduct(@Path("id") long productId);

    @GET("categories/")
    Call<ProductsItem> getCategories(@QueryMap Map<String, String> map);

    @GET("images/")
    Call<ProductsItem> getImages(@QueryMap Map<String, String> map);




}
