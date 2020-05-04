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
import com.example.finalprojectinpractice.view.fragment.HomeFragment;
import com.example.finalprojectinpractice.view.fragment.ProfileFragment;

public class EditActivity extends AppCompatActivity {

    private UserModel userModelID;
    private AppDatabase appDatabase;
    private TextView tvBack;
    private EditText etUsername, etEmail, etPassword, etPasswordConfirm;
    private Button btnEdit;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        intent.putExtra("Check", 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        userModelID = (UserModel) getIntent().getSerializableExtra("UserEdit");
        appDatabase = AppDatabase.initDatabase(getApplicationContext());

        tvBack = findViewById(R.id.editActivity_textView_BackToProfileRequest);
        etUsername = findViewById(R.id.editActivity_editTextUserName);
        etEmail = findViewById(R.id.editActivity_editTextEmail);
        etPassword = findViewById(R.id.editActivity_editTextPassword);
        etPasswordConfirm = findViewById(R.id.editActivity_editTextPasswordConfirm);
        btnEdit = findViewById(R.id.editActivity_buttonEdit);

        etUsername.setText(userModelID.getUsername());
        etEmail.setText(userModelID.getEmail());
        etPassword.setText(userModelID.getPassword());
        etPasswordConfirm.setText(userModelID.getPassword());

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(EditActivity.this, MainActivity.class);
                intentBack.putExtra("UserSession", userModelID);
                startActivity(intentBack);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    try {
                        UserModel userModel = new UserModel();
                        userModel.setId(userModelID.getId());
                        userModel.setUsername(etUsername.getText().toString());
                        userModel.setEmail(etEmail.getText().toString());
                        userModel.setPassword(etPassword.getText().toString());

                        appDatabase.userDAO().updateDataUser(userModel.getEmail(), userModel.getUsername(), userModel.getPassword(), userModelID.getId());

                        Log.d("EditActivity", "Edit Data Sukses");
                        Toast.makeText(getApplicationContext(), "Edit Data User Sukses", Toast.LENGTH_LONG).show();

                        Intent intentGoToProfile = new Intent(EditActivity.this, MainActivity.class);
                        intentGoToProfile.putExtra("Check", 7);
                        intentGoToProfile.putExtra("UserSession", userModel);
                        startActivity(intentGoToProfile);

                    } catch (Exception ex) {
                        Log.e("EditActivity", "gagal mengubah data user , msg: " + ex.getMessage());
                        Toast.makeText(getApplicationContext(), "Edit Data User Gagal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
