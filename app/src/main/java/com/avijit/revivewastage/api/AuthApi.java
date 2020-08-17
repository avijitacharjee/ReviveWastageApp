package com.avijit.revivewastage.api;

import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 4:09 PM.
 * Email: avijitach@gmail.com.
 */
public interface AuthApi {
    @Headers({"Accept:*/*","Content-type:application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("login")
    Call<NetworkResponse<User>> login(@Field("email")String email,@Field("password")String password);

    @POST("register")
    Call<NetworkResponse<User>> register(@Body User user);
    @POST("login")
    Call<String> abc(@Body User user);
}
