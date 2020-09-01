package com.avijit.revivewastage.api;

import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 4:16 PM.
 * Email: avijitach@gmail.com.
 */
public interface ProductApi {
    @GET("product")
    Call<NetworkResponse<List<Product>>> getAllProducts();
    @POST("product")
    Call<NetworkResponse<Product>> storeProduct(@Body Product product);
}
