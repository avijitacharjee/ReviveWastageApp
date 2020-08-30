package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SellProduct extends AppCompatActivity {
    private static final String TAG = "SellProduct";
    EditText productNameEditText,quantityEditText,priceEditText,productDetailsEditText;
    TextView selectImageButton,goButton;
    ImageView productImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_product);
        productNameEditText = findViewById(R.id.product_name_edit_text);
        quantityEditText = findViewById(R.id.quantity_edit_text);
        priceEditText = findViewById(R.id.price_edit_text);
        productDetailsEditText = findViewById(R.id.product_details_edit_text);
        selectImageButton = findViewById(R.id.product_image_text_view);
        productImageView =  findViewById(R.id.product_image_view);
        goButton = findViewById(R.id.go);
        goButton.setOnClickListener(v->{
            RequestQueue requestQueue = Volley.newRequestQueue(SellProduct.this);
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
            requestQueue.add(stringRequest);
        });
    }
}