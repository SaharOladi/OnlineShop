package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("date_created")
    private String mDateCreate;
    @SerializedName("src")
    private String mSrc;

    public Images(long id, String name, String dateCreate, String src) {
        mId = id;
        mName = name;
        mDateCreate = dateCreate;
        mSrc = src;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDateCreate() {
        return mDateCreate;
    }

    public String getSrc() {
        return mSrc;
    }
}
