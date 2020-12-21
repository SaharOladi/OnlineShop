package com.example.onlineshop.nerwork;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.onlineshop.nerwork.NetworkParam.BASE_URL;
import static com.example.onlineshop.nerwork.NetworkParam.PASSWORD;
import static com.example.onlineshop.nerwork.NetworkParam.USER_NAME;


public class RetrofitInstance {

    public static Retrofit getInstance() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

}
