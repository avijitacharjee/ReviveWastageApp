package com.avijit.revivewastage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
            startActivity(new Intent(getApplicationContext(),BuyProduct.class));
        });
        sellProductButton.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),SellProduct.class));
        });
    }
}