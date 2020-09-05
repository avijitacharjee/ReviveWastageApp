package com.avijit.revivewastage.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.revivewastage.model.Category;
import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.repository.CategoryRepository;

import java.util.List;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 9:24 PM.
 * Email: avijitach@gmail.com.
 */
public class CategoryViewModel extends ViewModel {
    CategoryRepository categoryRepository;
    MutableLiveData<NetworkResponse<List<Category>>> categoryList;
    public CategoryViewModel(){
        categoryRepository = CategoryRepository.getInstance();
    }
    public MutableLiveData<NetworkResponse<List<Category>>> all(){
        if(categoryList==null){
            categoryList = categoryRepository.getAllCategories();
        }
        return categoryList;
    }
    public MutableLiveData<NetworkResponse<Category>> store(Category category){
        return categoryRepository.store(category);
    }
    public MutableLiveData<NetworkResponse<Category>> update(Category category){
        return categoryRepository.update(category);
    }
    public MutableLiveData<NetworkResponse<Category>> delete(Category category){
        return categoryRepository.delete(category);
    }
}
