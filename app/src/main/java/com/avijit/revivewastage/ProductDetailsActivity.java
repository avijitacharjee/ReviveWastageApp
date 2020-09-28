package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avijit.revivewastage.adapter.BidsRecyclerViewAdapter;
import com.avijit.revivewastage.databinding.ActivityProductDetailsBinding;
import com.avijit.revivewastage.model.Bid;
import com.avijit.revivewastage.model.Product;
import com.avijit.revivewastage.model.User;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.ProductViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDetailsActivity extends BaseActivity {
    private static final String TAG = "ProductDetailsActivity";
    ActivityProductDetailsBinding binding;
    Product product;
    User user;
    ProductViewModel viewModel;
    List<Bid> bids;
    BidsRecyclerViewAdapter adapter;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUtils = new AppUtils(this);
        viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        bids = new ArrayList<>();
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        try {
            product = new Gson().fromJson(Objects.requireNonNull(getIntent().getExtras()).getString("product"),Product.class);
            JSONObject jsonObject = new JSONObject(getSharedPreferences("Revive",MODE_PRIVATE).getString("user",""));
            JSONObject data = jsonObject.getJSONObject("data");
            userId = data.getString("id");
        }catch (Exception e){
            Log.d(TAG, "onCreate: "+e);
        }
        binding.productNameTextView.setText(product.getName());
        binding.priceTextView.setText("$"+product.getPrice());
        binding.detailsTextView.setText(product.getDetails());
        Picasso.get().load("http://finalproject.xyz/revive_wastage/images/"+product.getImage()).into(binding.productImageView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new BidsRecyclerViewAdapter(bids);
        binding.recyclerView.setAdapter(adapter);

        setBids();
        binding.bidButton.setOnClickListener(v->{
            Bid bid = new Bid();
            bid.setAmount(binding.bidAmountEditText.getText().toString());
            bid.setDescription(binding.bidDescriptionEditText.getText().toString());
            bid.setProduct_id(product.getId());
            bid.setUser_id(userId);
            appUtils.dialog.show();
            viewModel.placeABid(bid).observe(this,response->{
                appUtils.dialog.dismiss();
                if(response.isNetworkSuccessful()){
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    bids.add(bid);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(this, "Connection timed out....", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
    private void setBids(){
        binding.progressDialog.setVisibility(View.VISIBLE);
        viewModel.getAllBids().observe(this,response->{
            if(response.isNetworkSuccessful()){
                binding.progressDialog.setVisibility(View.INVISIBLE);
                bids.clear();
                bids.addAll(Bid.filterByProductId(response.getData(),product.getId()));
                adapter.notifyDataSetChanged();
            }else {
                Toast.makeText(this, "Connection timed out....", Toast.LENGTH_SHORT).show();
            }
        });
    }
}