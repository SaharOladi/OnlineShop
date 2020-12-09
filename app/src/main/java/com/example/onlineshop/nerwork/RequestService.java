package com.example.onlineshop.nerwork;

import com.example.onlineshop.model.Images;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.ProductCategory;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RequestService {

    @GET("products/")
    Call<List<Product>> getProducts(@QueryMap Map<String, String> map);

    @GET("products/{id}/")
    Call<Product> getProduct(@Path("id") long productId);

    @GET("categories/")
    Call<List<ProductCategory>> getCategories(@QueryMap Map<String, String> map);

    @GET("images/")
    Call<List<Images>> getImages(@QueryMap Map<String, String> map);




}
