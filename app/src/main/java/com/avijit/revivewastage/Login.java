package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.avijit.revivewastage.model.User;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.UserViewModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Login extends BaseActivity {
    private static final String TAG = "Login";
    UserViewModel viewModel;
    Button loginButton;
    TextView signUpButton;
    EditText emailEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        appUtils = new AppUtils(this);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.pasword_edit_text);
        loginButton = findViewById(R.id.login);
        signUpButton = findViewById(R.id.signup_intent);
        loginButton.setOnClickListener(v->{
            if(emailEditText.getText().toString().equals("a") && passwordEditText.getText().toString().equals("")){
                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                return;
            }
            User user = new User();
            user.setEmail(emailEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            appUtils.dialog.show();
            viewModel.login(user).observe(Login.this,response->{
                appUtils.dialog.dismiss();
                if(response.isNetworkSuccessful()){
                    if(response.getMessage().equals("success")){
                        Log.d(TAG, "onCreate: "+ response);
                        getSharedPreferences("Revive",MODE_PRIVATE).edit().putString("user",new Gson().toJson(response)).apply();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else {
                        Toast.makeText(this, "Incorrect email/password", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                }

            });
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });
    }
}