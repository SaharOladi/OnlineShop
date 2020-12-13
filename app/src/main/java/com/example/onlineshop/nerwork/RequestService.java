package com.example.onlineshop.nerwork;


import com.example.onlineshop.model.MainResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RequestService {

    @GET("products/")
    Call<MainResponse> getProducts(@QueryMap Map<String, String> map);

    @GET("products/{id}/")
    Call<MainResponse> getProduct(@Path("id") long productId);

    @GET("categories/")
    Call<MainResponse> getCategories(@QueryMap Map<String, String> map);

    @GET("images/")
    Call<MainResponse> getImages(@QueryMap Map<String, String> map);




}
