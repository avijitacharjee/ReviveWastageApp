package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.avijit.revivewastage.databinding.ActivityMyProductsForSellBinding;
import com.avijit.revivewastage.fragment.buyer.ProductsFragment;
import com.avijit.revivewastage.utils.AppUtils;

public class MyProductsForSell extends BaseActivity {
    private static final String TAG = "MyProductsForSell";
    ActivityMyProductsForSellBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProductsForSellBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        appUtils = new AppUtils(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.my_product_for_sell_container,getProductFragmentForSeller())
                .commit();
    }
    private ProductsFragment getProductFragmentForSeller(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("seller", true);
        ProductsFragment productsFragment = new ProductsFragment();
        productsFragment.setArguments(bundle);
        return productsFragment;
    }
}