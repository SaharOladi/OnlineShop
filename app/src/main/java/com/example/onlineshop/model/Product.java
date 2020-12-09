package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("permalink")
    private String mPermaLink;
    @SerializedName("date_created")
    private String mDateCreate;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("on_sale")
    private boolean mOnSale;
    @SerializedName("total_sales")
    private int mTotalSale;
    @SerializedName("purchasable")
    private boolean mPurchasable;
    @SerializedName("average_rating")
    private String mAverageRating;
    @SerializedName("related_ids")
    private List<Long> mRelatedIds;
    @SerializedName("images")
    private List<Images> mImages;
    @SerializedName("categories")
    private List<ProductCategory> mProductCategories;

    public Product() {
    }

    public List<Images> getImages() {
        return mImages;
    }

    public void setImages(List<Images> images) {
        mImages = images;
    }

    public List<ProductCategory> getProductCategories() {
        return mProductCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        mProductCategories = productCategories;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPermaLink() {
        return mPermaLink;
    }

    public void setPermaLink(String permaLink) {
        mPermaLink = permaLink;
    }

    public String getDateCreate() {
        return mDateCreate;
    }

    public void setDateCreate(String dateCreate) {
        mDateCreate = dateCreate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public boolean isOnSale() {
        return mOnSale;
    }

    public void setOnSale(boolean onSale) {
        mOnSale = onSale;
    }

    public int getTotalSale() {
        return mTotalSale;
    }

    public void setTotalSale(int totalSale) {
        mTotalSale = totalSale;
    }

    public boolean isPurchasable() {
        return mPurchasable;
    }

    public void setPurchasable(boolean purchasable) {
        mPurchasable = purchasable;
    }

    public String getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(String averageRating) {
        mAverageRating = averageRating;
    }

    public List<Long> getRelatedIds() {
        return mRelatedIds;
    }

    public void setRelatedIds(List<Long> relatedIds) {
        mRelatedIds = relatedIds;
    }
}
