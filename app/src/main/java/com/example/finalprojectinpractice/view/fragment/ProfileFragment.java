package com.example.finalprojectinpractice.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.database.AppDatabase;
import com.example.finalprojectinpractice.database.UserModel;
import com.example.finalprojectinpractice.view.activity.EditActivity;
import com.example.finalprojectinpractice.view.activity.LoginActivity;
import com.example.finalprojectinpractice.view.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private UserModel userModel;
    private AppDatabase appDatabase;
    private TextView tvUsername, tvEmail, tvPassword;
    private Button btnLogout, btnEdit, btnDelete;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userModel = MainActivity.userModel;
        appDatabase = AppDatabase.initDatabase(getContext());

        tvUsername = view.findViewById(R.id.fragmentProfile_Username);
        tvUsername.setText(userModel.getUsername());

        tvEmail = view.findViewById(R.id.fragmentProfile_Email);
        tvEmail.setText(userModel.getEmail());

        tvPassword = view.findViewById(R.id.fragmentProfile_Password);
        tvPassword.setText(userModel.getPassword());

        btnLogout = view.findViewById(R.id.fragmentProfile_btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogout);
            }
        });

        btnEdit = view.findViewById(R.id.fragmentProfile_btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(getActivity(), EditActivity.class);
                intentEdit.putExtra("UserEdit", userModel);
                startActivity(intentEdit);
            }
        });

        btnDelete = view.findViewById(R.id.fragmentProfile_btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    appDatabase.userDAO().deleteDataUser(userModel.getEmail());

                    Log.d("ProfileFragment", "Delete Account Sukses");
                    Toast.makeText(getContext(), "Delete Account User Sukses", Toast.LENGTH_LONG).show();

                    Intent intentGoToLogin = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intentGoToLogin);
                } catch (Exception ex) {
                    Log.d("ProfileFragment", "Delete Account Gagal, msg: " + ex.getMessage());
                    Toast.makeText(getContext(), "Delete Account User Gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
