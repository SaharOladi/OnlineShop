package com.example.onlineshop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.MainResponse;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.nerwork.NetworkParam;
import com.example.onlineshop.nerwork.RequestService;
import com.example.onlineshop.nerwork.RetrofitInstance;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private List<ProductsItem> mProducts;
    private RequestService mRequestService;

    private final String TAG = "Repository";

    public List<ProductsItem> getProducts() {
        return mProducts;
    }

    public void setProducts(List<ProductsItem> products) {
        mProducts = products;
    }

    public Repository() {
        mRequestService = RetrofitInstance.getInstance().create(RequestService.class);
    }

    public void fetchAllProductItemsAsync(Callbacks callBacks) {
        Call<List<ProductsItem>> call = mRequestService.getProducts(NetworkParam.BASE);
        call.enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {
                List<ProductsItem> items = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(items);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

    public interface Callbacks {
        void onItemResponse(List<ProductsItem> items);
    }
}
