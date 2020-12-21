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

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(USER_NAME, PASSWORD))
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private static class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        public BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
            return chain.proceed(authenticatedRequest);
        }

    }

}
