package com.adityaputrak.bukumedia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adityaputrak.bukumedia.R;
import com.adityaputrak.bukumedia.admin.DashboardAdminActivity;

import Api.ApiClient;
import Model.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static EditText edtUsername, edtPass;
    public static ImageView btnLogin;
    public static TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.etUsername);
        edtPass = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.txtRegist);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginproses();
            }
        });
    }

    private void Loginproses() {
        final String username = edtUsername.getText().toString();
        final String pass = edtPass.getText().toString();

        ApiClient.getServices().loginUser(username, pass).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==1){
                        Intent i = new Intent(LoginActivity.this, DashboardUserActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "GAGAL"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}