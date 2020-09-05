package com.avijit.revivewastage.repository;

import androidx.lifecycle.MutableLiveData;

import com.avijit.revivewastage.api.ProductApi;
import com.avijit.revivewastage.api.RetrofitService;
import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.Product;

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
        productApi.storeProduct(product).enqueue(new Callback<NetworkResponse<Product>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Product>> call, Response<NetworkResponse<Product>> response) {
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
}
