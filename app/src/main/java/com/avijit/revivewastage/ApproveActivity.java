package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.revivewastage.databinding.ActivityApproveBinding;
import com.avijit.revivewastage.model.Bid;
import com.google.gson.Gson;

public class ApproveActivity extends AppCompatActivity {
    ActivityApproveBinding binding;
    Bid bid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApproveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bid= new Bid();
        try {
            bid = new Gson().fromJson(getIntent().getExtras().getString("bid"),Bid.class);
        }catch (Exception e){}
        binding.nameTextView.setText(bid.getName());
        binding.phoneTextView.setText(bid.getPhone());
    }
}