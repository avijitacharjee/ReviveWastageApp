package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    TextView nameTextView,emailTextView,phoneTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameTextView = findViewById(R.id.name_text_view);
        emailTextView = findViewById(R.id.email_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        try {
            JSONObject jsonObject = new JSONObject(getSharedPreferences("Revive",MODE_PRIVATE).getString("user",""));
            JSONObject data = jsonObject.getJSONObject("data");
            nameTextView.setText(data.getString("name"));
            emailTextView.setText(data.getString("email"));
            phoneTextView.setText(data.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}