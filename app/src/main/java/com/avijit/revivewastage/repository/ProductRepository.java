package com.avijit.revivewastage.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.avijit.revivewastage.api.ProductApi;
import com.avijit.revivewastage.api.RetrofitService;
import com.avijit.revivewastage.model.Bid;
import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.Product;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Avijit Acharjee on 9/1/2020 at 4:45 PM.
 * Email: avijitach@gmail.com.
 */

public class ProductRepository {
    private ProductApi productApi;
    private static ProductRepository productRepository;
    private static final String TAG = "ProductRepository";
    public static ProductRepository getInstance(){
        if(productRepository==null){
            productRepository=new ProductRepository();
        }
        return productRepository;
    }
    private ProductRepository(){
        productApi = RetrofitService.createService(ProductApi.class);
    }
    public MutableLiveData<NetworkResponse<List<Product>>> getAllProducts(){
        MutableLiveData<NetworkResponse<List<Product>>> result = new MutableLiveData<>();
        NetworkResponse<List<Product>> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        productApi.getAllProducts().enqueue(new Callback<NetworkResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<NetworkResponse<List<Product>>> call, Response<NetworkResponse<List<Product>>> response) {
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }
            @Override
            public void onFailure(Call<NetworkResponse<List<Product>>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }
    public MutableLiveData<NetworkResponse<Product>> store(Product product){
        MutableLiveData<NetworkResponse<Product>> result = new MutableLiveData<>();
        NetworkResponse<Product> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        productApi.storeProduct(product.getName(),product.getQuantity(),product.getPrice(),product.getDetails(),product.getImage(),product.getCategory_id()).enqueue(new Callback<NetworkResponse<Product>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Product>> call, Response<NetworkResponse<Product>> response) {
                Log.d(TAG, "onResponse: "+new Gson().toJson(response));
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Product>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }

    public MutableLiveData<NetworkResponse<List<Bid>>> getAllBids(){
        MutableLiveData<NetworkResponse<List<Bid>>> result = new MutableLiveData<>();
        NetworkResponse<List<Bid>> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        productApi.getAllBids().enqueue(new Callback<NetworkResponse<List<Bid>>>() {
            @Override
            public void onResponse(Call<NetworkResponse<List<Bid>>> call, Response<NetworkResponse<List<Bid>>> response) {
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<List<Bid>>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }
    public MutableLiveData<NetworkResponse<Bid>> placeABid(Bid bid){
        MutableLiveData<NetworkResponse<Bid>> result = new MutableLiveData<>();
        NetworkResponse<Bid> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        productApi.placeABit(bid.getDescription(),bid.getAmount(),bid.getProduct_id(),bid.getUser_id()).enqueue(new Callback<NetworkResponse<Bid>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Bid>> call, Response<NetworkResponse<Bid>> response) {
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Bid>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }

    public MutableLiveData<NetworkResponse<Bid>> approveBid(Bid bid){
        MutableLiveData<NetworkResponse<Bid>> result = new MutableLiveData<>();
        NetworkResponse<Bid> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        productApi.approveBid(bid.getId(),bid.getProduct_id()).enqueue(new Callback<NetworkResponse<Bid>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Bid>> call, Response<NetworkResponse<Bid>> response) {
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Bid>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }
}
