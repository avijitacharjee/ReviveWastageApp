package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.revivewastage.fragment.buyer.ProductsFragment;
import com.avijit.revivewastage.fragment.buyer.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class BuyProduct extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
        bottomNavigationView = findViewById(R.id.bn);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.buyer_container,new ProductsFragment())
                .commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.products_bn: {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.buyer_container,new ProductsFragment())
                            .commit();
                    return true;
                }
                case R.id.profile_bn : {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.buyer_container,new ProfileFragment())
                            .commit();
                    return true;
                }
            }
            return false;
        });

    }
}