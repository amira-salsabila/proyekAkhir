package com.example.finalprojectinpractice.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.database.AppDatabase;
import com.example.finalprojectinpractice.database.UserModel;

public class SignupActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private TextView tvLoginRequest;
    private EditText etEmail, etPassword, etPasswordConfirm, etUsername;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvLoginRequest = findViewById(R.id.signupActivity_textView_LoginRequest);
        etUsername = findViewById(R.id.signupActivity_editTextUserName);
        etEmail = findViewById(R.id.signupActivity_editTextEmail);
        etPassword = findViewById(R.id.signupActivity_editTextPassword);
        etPasswordConfirm = findViewById(R.id.signupActivity_editTextPasswordConfirm);
        btnSignup = findViewById(R.id.signupActivity_buttonSignUp);

        appDatabase = AppDatabase.initDatabase(getApplicationContext());

        tvLoginRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToSignUp = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intentGoToSignUp);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    try {
                        UserModel userModel = new UserModel();
                        userModel.setUsername(etUsername.getText().toString());
                        userModel.setEmail(etEmail.getText().toString());
                        userModel.setPassword(etPassword.getText().toString());

                        appDatabase.userDAO().insertUser(userModel);

                        Log.d("SignupActivity", "Sign Up Sukses");
                        Toast.makeText(getApplicationContext(), "Sign Up User Sukses", Toast.LENGTH_LONG).show();

                        Intent intentGoToSignUp = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intentGoToSignUp);

                    } catch (Exception ex) {
                        Log.e("SignupActivity", "gagal menyimpan data user baru , msg: " + ex.getMessage());
                        Toast.makeText(getApplicationContext(), "Sign Up User Gagal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
