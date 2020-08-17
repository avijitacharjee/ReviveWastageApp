package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {
    TextView profileButton,sellProductButton,buyProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileButton = findViewById(R.id.profile_button);
        sellProductButton = findViewById(R.id.sell_product);
        buyProductButton = findViewById(R.id.buy_product);
        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Profile.class));
        });
        buyProductButton.setOnClickListener(v->{

        });
        sellProductButton.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),SellProduct.class));
        });
    }
}