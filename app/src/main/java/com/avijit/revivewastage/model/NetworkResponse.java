package com.avijit.revivewastage.model;


import com.google.gson.Gson;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 4:13 PM.
 * Email: avijitach@gmail.com.
 */

public class NetworkResponse<T> {
    private T data;
    private String message;
    private boolean isNetworkSuccessful;

    public NetworkResponse() {
        setNetworkSuccessful(true);
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNetworkSuccessful() {
        return isNetworkSuccessful;
    }

    public void setNetworkSuccessful(boolean networkSuccessful) {
        isNetworkSuccessful = networkSuccessful;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
