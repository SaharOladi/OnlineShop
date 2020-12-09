package com.example.onlineshop.repository;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.nerwork.NetworkParam;
import com.example.onlineshop.nerwork.RequestService;
import com.example.onlineshop.nerwork.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {

    private List<Product> mProducts;
    private RequestService mRequestService;

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public Repository() {
        mRequestService = RetrofitInstance.getInstance().create(RequestService.class);
    }

    public List<Product> fetchProduct(){

        Call<List<Product>> call = mRequestService.getProducts(NetworkParam.PRODUCT);
        try {
            Response<List<Product>> response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
