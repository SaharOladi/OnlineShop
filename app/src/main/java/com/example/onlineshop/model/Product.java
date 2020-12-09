package com.example.onlineshop.model;


import com.example.onlineshop.nerwork.model.CategoriesItem;
import com.example.onlineshop.nerwork.model.ImagesItem;

import java.util.List;

public class Product {

    private long mId;
    private String mName;
    private String mPermaLink;
    private String mDateCreate;
    private String mDescription;
    private String mPrice;
    private boolean mOnSale;
    private int mTotalSale;
    private boolean mPurchasable;
    private String mAverageRating;
    private List<Integer> mRelatedIds;
    private List<ImagesItem> mImages;
    private List<CategoriesItem> mProductCategories;

    public Product(long id, String name, String permaLink, String dateCreate, String description,
                   String price, boolean onSale, int totalSale, boolean purchasable,
                   String averageRating, List<Integer> relatedIds, List<ImagesItem> images,
                   List<CategoriesItem> productCategories) {
        mId = id;
        mName = name;
        mPermaLink = permaLink;
        mDateCreate = dateCreate;
        mDescription = description;
        mPrice = price;
        mOnSale = onSale;
        mTotalSale = totalSale;
        mPurchasable = purchasable;
        mAverageRating = averageRating;
        mRelatedIds = relatedIds;
        mImages = images;
        mProductCategories = productCategories;
    }

    public List<ImagesItem> getImages() {
        return mImages;
    }

    public void setImages(List<ImagesItem> images) {
        mImages = images;
    }

    public List<CategoriesItem> getProductCategories() {
        return mProductCategories;
    }

    public void setProductCategories(List<CategoriesItem> productCategories) {
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

    public List<Integer> getRelatedIds() {
        return mRelatedIds;
    }

    public void setRelatedIds(List<Integer> relatedIds) {
        mRelatedIds = relatedIds;
    }
}
