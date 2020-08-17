package com.avijit.revivewastage.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.revivewastage.model.NetworkResponse;
import com.avijit.revivewastage.model.User;
import com.avijit.revivewastage.repository.UserRepository;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 4:47 PM.
 * Email: avijitach@gmail.com.
 */
public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    public UserViewModel(){
        userRepository = new UserRepository();
    }
    public MutableLiveData<NetworkResponse<User>> register(User user){
        return userRepository.register(user);
    }
    public MutableLiveData<NetworkResponse<User>> login(User user){
        return userRepository.login(user);
    }
}
