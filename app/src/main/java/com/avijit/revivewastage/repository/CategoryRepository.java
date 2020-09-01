package com.avijit.revivewastage.repository;

import androidx.lifecycle.MutableLiveData;

import com.avijit.revivewastage.api.CategoryApi;
import com.avijit.revivewastage.api.RetrofitService;
import com.avijit.revivewastage.model.Category;
import com.avijit.revivewastage.model.NetworkResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 9:11 PM.
 * Email: avijitach@gmail.com.
 */
public class CategoryRepository {
    private CategoryApi categoryApi;
    private static CategoryRepository categoryRepository;

    public static CategoryRepository getInstance() {
        if (categoryRepository == null) {
            categoryRepository = new CategoryRepository();
        }
        return categoryRepository;
    }

    private CategoryRepository() {
        categoryApi = RetrofitService.createService(CategoryApi.class);
    }

    public MutableLiveData<NetworkResponse<List<Category>>> getAllCategories() {
        MutableLiveData<NetworkResponse<List<Category>>> result = new MutableLiveData<>();
        NetworkResponse<List<Category>> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        categoryApi.getAllCategories().enqueue(new Callback<NetworkResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<NetworkResponse<List<Category>>> call, Response<NetworkResponse<List<Category>>> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<List<Category>>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }

    public MutableLiveData<NetworkResponse<Category>> store(Category category) {
        MutableLiveData<NetworkResponse<Category>> result = new MutableLiveData<>();
        NetworkResponse<Category> fail = new NetworkResponse<>();
        fail.setNetworkSuccessful(false);
        categoryApi.store(category).enqueue(new Callback<NetworkResponse<Category>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Category>> call, Response<NetworkResponse<Category>> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    result.setValue(fail);
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Category>> call, Throwable t) {
                result.setValue(fail);
            }
        });
        return result;
    }
}
