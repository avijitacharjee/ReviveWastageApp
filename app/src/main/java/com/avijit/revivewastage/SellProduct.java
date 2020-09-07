package com.avijit.revivewastage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.revivewastage.model.Category;
import com.avijit.revivewastage.model.Product;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.SellProductViewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellProduct extends AppCompatActivity {
    private static final String TAG = "SellProduct";
    EditText productNameEditText,quantityEditText,priceEditText,productDetailsEditText;
    TextView selectImageButton,goButton;
    ImageView productImageView;
    Spinner catSpinner;
    List<Category> categoryList;
    List<String> categoryStringList;
    SellProductViewModel viewModel;
    int PICK_IMAGE_REQUEST=111;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_product);
        AppUtils appUtils = new AppUtils(this);
        productNameEditText = findViewById(R.id.product_name_edit_text);
        quantityEditText = findViewById(R.id.quantity_edit_text);
        priceEditText = findViewById(R.id.price_edit_text);
        productDetailsEditText = findViewById(R.id.product_details_edit_text);
        selectImageButton = findViewById(R.id.product_image_text_view);
        productImageView =  findViewById(R.id.product_image_view);
        catSpinner = findViewById(R.id.cat_spinner);
        categoryList = new ArrayList<>();
        categoryStringList = new ArrayList<>();
        categoryStringList.add("--Select Category--");
        viewModel = ViewModelProviders.of(this).get(SellProductViewModel.class);
        appUtils.dialog.show();
        viewModel.getAllCategories().observe(this,response->{
            appUtils.dialog.dismiss();
            if(response.isNetworkSuccessful()){
                categoryList.addAll(response.getData());
                categoryStringList.addAll(Category.catListToStringList(categoryList));
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryStringList);
        catSpinner.setAdapter(adapter);
        goButton = findViewById(R.id.go);

        goButton.setOnClickListener(v->{
            if(!valid()){
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }
            appUtils.dialog.show();

            Product product = new Product();
            product.setName(productNameEditText.getText().toString());
            product.setPrice(priceEditText.getText().toString());
            product.setQuantity(quantityEditText.getText().toString());
            product.setDetails(productDetailsEditText.getText().toString());
            product.setImage(appUtils.bitmapToString(bitmap));
            product.setCategory_id(categoryList.get(catSpinner.getSelectedItemPosition()-1).getId());
            viewModel.store(product).observe(this,response->{
                appUtils.dialog.dismiss();
                if(response.isNetworkSuccessful()){
                    Toast.makeText(this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                }
            });
            /*RequestQueue requestQueue = Volley.newRequestQueue(SellProduct.this);
            String url = "https://finalproject.xyz/revive_wastage/api.php/product";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                Log.d(TAG, "onResponse: "+response);
                Toast.makeText(SellProduct.this, response, Toast.LENGTH_SHORT).show();
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SellProduct.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return super.getHeaders();
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("name",productNameEditText.getText().toString());
                    params.put("price",priceEditText.getText().toString());
                    params.put("quantity",quantityEditText.getText().toString());
                    params.put("details",productDetailsEditText.getText().toString());
                    params.put("category_id","1");
                    params.put("image","null");
                    return params;
                }
            };
            requestQueue.add(stringRequest);*/
        });
        selectImageButton.setOnClickListener(v->{
            chooseImage(v);
        });
    }
    public void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FILE );
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                productImageView.setImageBitmap(bitmap);
            }catch (Exception e){
                Log.d(TAG, "onActivityResult: "+e);
            }
        }
    }
    private boolean valid(){
        if(catSpinner.getSelectedItemPosition()<1 || bitmap==null || productNameEditText.getText().toString().equalsIgnoreCase("") || priceEditText.getText().toString().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }
}