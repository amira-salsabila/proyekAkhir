package com.example.finalprojectinpractice.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.database.AppDatabase;
import com.example.finalprojectinpractice.database.UserDAO;
import com.example.finalprojectinpractice.database.UserModel;

public class LoginActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private TextView tvSignUpRequest;
    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvSignUpRequest = findViewById(R.id.loginActivity_textView_RegistRequest);
        etEmail = findViewById(R.id.loginActivity_editTextEmail);
        etPassword = findViewById(R.id.loginActivity_editTextPassword);
        btnLogin = findViewById(R.id.loginActivity_buttonLogin);

        appDatabase = AppDatabase.initDatabase(getApplicationContext());

        tvSignUpRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intentGoToSignUp);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                UserModel user = appDatabase.userDAO().cekUserLogin(email, password);

                if (user != null) {
                    Intent intentLoginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                    intentLoginSuccess.putExtra("UserSession", user);
                    startActivity(intentLoginSuccess);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
