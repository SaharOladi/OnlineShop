package com.example.onlineshop.repository;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.nerwork.NetworkParam;
import com.example.onlineshop.nerwork.RequestService;
import com.example.onlineshop.nerwork.RetrofitInstance;
import com.example.onlineshop.nerwork.model.MainResponse;
import com.example.onlineshop.nerwork.model.ProductsItem;

import java.io.IOException;
import java.util.ArrayList;
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

    public List<Product> fetchProduct() {

        Call<MainResponse> call = mRequestService.getProducts(NetworkParam.PRODUCT);
        List<Product> products = new ArrayList<>();

        try {
            Response<MainResponse> response = call.execute();
            MainResponse mainResponse = response.body();
            for (ProductsItem productsItem : mainResponse.getProducts()) {
                Product product = new Product(productsItem.getId(), productsItem.getName(),
                        productsItem.getPermalink(), productsItem.getDateCreated(), productsItem.getDescription(),
                        productsItem.getPrice(), productsItem.isOnSale(), productsItem.getTotalSales(),
                        productsItem.isPurchasable(), productsItem.getAverageRating(), productsItem.getRelatedIds(),
                        productsItem.getImages(), productsItem.getCategories());

                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return products;

        }
    }
}
