package com.avijit.revivewastage.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avijit.revivewastage.model.Bid;
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
    private MutableLiveData<NetworkResponse<List<Product>>> products;
    private MutableLiveData<NetworkResponse<List<Bid>>> bids;
    public ProductViewModel(){
        productRepository = ProductRepository.getInstance();
    }
    public MutableLiveData<NetworkResponse<List<Product>>> all(){
        if(products==null){
            products = productRepository.getAllProducts();
        }
        return products;
    }
    public MutableLiveData<NetworkResponse<List<Bid>>> getAllBids(){
        if(bids==null) {
            bids = productRepository.getAllBids();
        }
        return bids;
    }
    public MutableLiveData<NetworkResponse<Bid>> placeABid(Bid bid){
        return productRepository.placeABid(bid);
    }
}
