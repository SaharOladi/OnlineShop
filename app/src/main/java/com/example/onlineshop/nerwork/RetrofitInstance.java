package com.example.onlineshop.nerwork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.onlineshop.nerwork.NetworkParam.BASE_URL;


public class RetrofitInstance {

    public static Retrofit getInstance() {


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

}
