package com.avijit.revivewastage.api;

import com.avijit.revivewastage.model.Bid;
import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 4:16 PM.
 * Email: avijitach@gmail.com.
 */
public interface ProductApi {
    @GET("product")
    Call<NetworkResponse<List<Product>>> getAllProducts();
    @FormUrlEncoded
    @POST("product")
    Call<NetworkResponse<Product>> storeProduct(@Field("name")String name,@Field("quantity")String quantity,@Field("price")String price,@Field("details")String details,@Field("image")String image,@Field("category_id")String category_id);

    @GET("bid")
    Call<NetworkResponse<List<Bid>>> getAllBids();

    @FormUrlEncoded
    @POST("place_bid")
    Call<NetworkResponse<Bid>> placeABit(@Field("description")String description,@Field("amount")String amount,@Field("product_id")String productId,@Field("user_id")String userId);

}
