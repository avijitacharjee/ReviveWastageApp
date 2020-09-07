package com.avijit.revivewastage.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.Product;
import com.avijit.revivewastage.repository.ProductRepository;

import java.util.List;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 4:44 PM.
 * Email: avijitach@gmail.com.
 */
public class ProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    public ProductViewModel(){
        productRepository = ProductRepository.getInstance();
    }
    public MutableLiveData<NetworkResponse<List<Product>>> all(){
        return productRepository.getAllProducts();
    }
}
