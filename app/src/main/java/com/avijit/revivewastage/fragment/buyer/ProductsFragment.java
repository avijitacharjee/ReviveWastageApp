package com.avijit.revivewastage.fragment.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.R;
import com.avijit.revivewastage.adapter.AllProductRecyclerViewAdapter;
import com.avijit.revivewastage.model.Product;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avijit Acharjee on 9/7/2020 at 6:46 PM.
 * Email: avijitach@gmail.com.
 */
public class ProductsFragment extends Fragment {
    RecyclerView productRecyclerView;
    AllProductRecyclerViewAdapter adapter;
    List<Product> productList;
    ProductViewModel viewModel;
    AppUtils appUtils;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products,null,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productRecyclerView = view.findViewById(R.id.all_product_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        productRecyclerView.setLayoutManager(layoutManager);
        productList = new ArrayList<>();
        adapter = new AllProductRecyclerViewAdapter(productList,getContext());
        productRecyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        appUtils = new AppUtils(getContext());
        appUtils.dialog.show();
        viewModel.all().observe(this,response->{
            appUtils.dialog.dismiss();
            if(response.isNetworkSuccessful()){
                productList.clear();
                productList.addAll(response.getData());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(getContext(), "Faiiled to connect..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
