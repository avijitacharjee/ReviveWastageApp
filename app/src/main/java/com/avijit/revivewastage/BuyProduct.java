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
    RecyclerView allProductRecyclerView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
        bottomNavigationView = findViewById(R.id.bn);
        allProductRecyclerView = findViewById(R.id.all_product_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        allProductRecyclerView.setLayoutManager(layoutManager);
        RequestQueue requestQueue = Volley.newRequestQueue(BuyProduct.this);

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

        String url = "https://finalproject.xyz/revive_wastage/api.php/product";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BuyProduct.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}