package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.avijit.revivewastage.databinding.ActivityProductDetailsBinding;
import com.avijit.revivewastage.model.Product;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductDetailsActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailsActivity";
    ActivityProductDetailsBinding binding;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        try {
            product = new Gson().fromJson(Objects.requireNonNull(getIntent().getExtras()).getString("product"),Product.class);
        }catch (Exception e){
            Log.d(TAG, "onCreate: "+e);
        }
        binding.productNameTextView.setText(product.getName());
        binding.priceTextView.setText("$"+product.getPrice());
        binding.detailsTextView.setText(product.getDetails());
        Picasso.get().load("http://finalproject.xyz/revive_wastage/images/"+product.getImage()).into(binding.productImageView);
    }
}