package com.avijit.revivewastage.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.avijit.revivewastage.api.AuthApi;
import com.avijit.revivewastage.api.RetrofitService;
import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 4:28 PM.
 * Email: avijitach@gmail.com.
 */
public class UserRepository {
    private static final String TAG = "UserRepository";
    public static UserRepository userRepository;
    AuthApi authApi;
    public static UserRepository getUserRepository() {
        if(userRepository==null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }
    public UserRepository(){
        authApi = RetrofitService.createService(AuthApi.class);
    }
    public MutableLiveData<NetworkResponse<User>> register(User user){
        final MutableLiveData<NetworkResponse<User>> result = new MutableLiveData<>();
        final NetworkResponse<User> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        authApi.register(user).enqueue(new Callback<NetworkResponse<User>>() {
            @Override
            public void onResponse(Call<NetworkResponse<User>> call, Response<NetworkResponse<User>> response) {
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<User>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }
    public MutableLiveData<NetworkResponse<User>> login(User user){
        Log.d(TAG, "login: "+user.toString());
        final MutableLiveData<NetworkResponse<User>> result = new MutableLiveData<>();
        final NetworkResponse<User> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        authApi.login(user.getEmail(),user.getPassword()).enqueue(new Callback<NetworkResponse<User>>() {
            @Override
            public void onResponse(Call<NetworkResponse<User>> call, Response<NetworkResponse<User>> response) {
                Log.d(TAG, "onResponse: "+new Gson().toJson(response));
                if(response.isSuccessful()){
                    result.setValue(response.body());
                }
                else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<User>> call, Throwable t) {
                Log.d(TAG, t.toString());
                result.setValue(fail);
            }
        });
        return result;
    }

}
