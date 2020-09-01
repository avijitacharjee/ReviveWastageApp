package com.avijit.revivewastage.api;

import com.avijit.revivewastage.model.Category;
import java.util.List;
import com.avijit.revivewastage.model.NetworkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 5:54 PM.
 * Email: avijitach@gmail.com.
 */
public interface CategoryApi {
    @GET("category")
    Call<NetworkResponse<List<Category>>> getAllCategories();

    @POST("category")
    Call<NetworkResponse<Category>> store(@Body Category category);
}
