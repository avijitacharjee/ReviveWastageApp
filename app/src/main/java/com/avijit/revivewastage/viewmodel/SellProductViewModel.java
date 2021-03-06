package com.avijit.revivewastage.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.revivewastage.model.Category;
import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.Product;
import com.avijit.revivewastage.repository.CategoryRepository;
import com.avijit.revivewastage.repository.ProductRepository;

import java.util.List;

/**
 * Created by Avijit Acharjee on 9/5/2020 at 5:40 PM.
 * Email: avijitach@gmail.com.
 */
public class SellProductViewModel extends ViewModel {
    CategoryRepository categoryRepository ;
    ProductRepository productRepository;
    MutableLiveData<NetworkResponse<List<Category>>> categoryList;
    public SellProductViewModel(){
        categoryRepository = CategoryRepository.getInstance();
        productRepository = ProductRepository.getInstance();
    }
    public MutableLiveData<NetworkResponse<List<Category>>> getAllCategories(){
        if(categoryList==null){
            categoryList = categoryRepository.getAllCategories();
        }
        return categoryList;
    }
    public MutableLiveData<NetworkResponse<Product>> store(Product product){
        return productRepository.store(product);
    }
}
