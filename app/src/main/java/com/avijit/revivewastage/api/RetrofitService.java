package com.avijit.revivewastage.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 4:07 PM.
 * Email: avijitach@gmail.com.
 */
public class RetrofitService {
    static {
        initialize();
    }
    static OkHttpClient okHttpClient;
    private static void initialize(){
        okHttpClient =new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit = new Retrofit.Builder().
            baseUrl("https://finalproject.xyz/revive_wastage/api.php/").
            addConverterFactory(GsonConverterFactory.create(gson)).
            client(okHttpClient).
            build();
    public static <S> S createService(Class<S> serviceClass)
    {
        return retrofit.create(serviceClass);
    }
}
