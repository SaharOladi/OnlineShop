package com.example.onlineshop.repository;

import com.example.onlineshop.model.MainResponse;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.nerwork.NetworkParam;
import com.example.onlineshop.nerwork.RequestService;
import com.example.onlineshop.nerwork.RetrofitInstance;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Repository {

    private List<ProductsItem> mProducts;
    private RequestService mRequestService;

    public List<ProductsItem> getProducts() {
        return mProducts;
    }

    public void setProducts(List<ProductsItem> products) {
        mProducts = products;
    }

    public Repository() {
        mRequestService = RetrofitInstance.getInstance().create(RequestService.class);
    }

    public List<ProductsItem> fetchProduct() {

        Call<MainResponse> call = mRequestService.getProducts(NetworkParam.PRODUCT);
        List<ProductsItem> products = new ArrayList<>();

        try {
            Response<MainResponse> response = call.execute();
            MainResponse mainResponse = response.body();
            for (ProductsItem productsItem : mainResponse.getProducts()) {
//                ProductsItem product = new ProductsItem(productsItem.getId(), productsItem.getName(),
//                        productsItem.getPermalink(), productsItem.getDateCreated(), productsItem.getDescription(),
//                        productsItem.getPrice(), productsItem.isOnSale(), productsItem.getTotalSales(),
//                        productsItem.isPurchasable(), productsItem.getAverageRating(), productsItem.getRelatedIds(),
//                        productsItem.getImages(), productsItem.getCategories());
//
//                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return products;

        }
    }
}
