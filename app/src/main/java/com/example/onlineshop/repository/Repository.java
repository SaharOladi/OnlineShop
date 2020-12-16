package com.example.onlineshop.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.MainResponse;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.nerwork.NetworkParam;
import com.example.onlineshop.nerwork.RequestService;
import com.example.onlineshop.nerwork.RetrofitInstance;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.onlineshop.nerwork.NetworkParam.CONSUMER_KEY;
import static com.example.onlineshop.nerwork.NetworkParam.CONSUMER_SECRET;

public class Repository {

    private final String TAG = "Repository";

    private List<ProductsItem> mProducts;
    private RequestService mRequestService;

    public static final Map<String, String> BASE = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);

    }};

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

        mRequestService.getProducts(BASE).enqueue(new Callback<List<ProductsItem>>() {
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

    public void fetchRecentProducts(int page, Callbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("orderby", "date");

        mRequestService.getProducts(localMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> recentItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(recentItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }


    public void fetchMostVisitedProducts(int page, Callbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("orderby", "rating");

        mRequestService.getProducts(localMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> mostVisitedItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(mostVisitedItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchRatedProducts(int page, Callbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("orderby", "popularity");

        mRequestService.getProducts(localMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> ratedItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(ratedItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchCategory(int page, CategoryCallbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("per_page", String.valueOf(10));

        mRequestService.getCategories(localMap).enqueue(new Callback<List<CategoriesItem>>() {
            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                List<CategoriesItem> categoriesItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(categoriesItems);
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

    public void fetchCategoryProduct(int page, int id, Callbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("category", String.valueOf(id));

        mRequestService.getProducts(localMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> categoryItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(categoryItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }


    public void fetchSingleProduct(int id, SingleCallbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);

        mRequestService.getSingleProduct(id, localMap).enqueue(new Callback<ProductsItem>() {
            @Override
            public void onResponse(Call<ProductsItem> call, Response<ProductsItem> response) {

                ProductsItem productsItem = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(productsItem);
            }

            @Override
            public void onFailure(Call<ProductsItem> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }



    public interface Callbacks {
        void onItemResponse(List<ProductsItem> items);
    }

    public interface CategoryCallbacks {
        void onItemResponse(List<CategoriesItem> items);
    }

    public interface SingleCallbacks {
        void onItemResponse(ProductsItem item);
    }

}
