package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

public class CategoriesItem {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("slug")
    private String slug;

    @SerializedName("image")
    private ImagesItem mImages;

    @SerializedName("count")
    private Integer mCount;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public ImagesItem getImages() {
        return mImages;
    }

    public Integer getCount() {
        return mCount;
    }

}
