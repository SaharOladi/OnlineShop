package com.example.onlineshop.model;

public class ProductCategory {

    private long mId;
    private String mName;
    private String mSlug;

    public ProductCategory(long id, String name, String slug) {
        mId = id;
        mName = name;
        mSlug = slug;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSlug() {
        return mSlug;
    }
}
