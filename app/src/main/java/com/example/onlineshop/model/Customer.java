package com.example.onlineshop.model;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("username")
    private String mUserName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id")
    private int mId;

    public Customer(String userName, String email) {
        mUserName = userName;
        mEmail = email;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
