package com.example.finalprojectinpractice.view.activity.typeAgencyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.adapter.typeAgencyAdapter.HospitalAdapter;
import com.example.finalprojectinpractice.database.UserModel;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import com.example.finalprojectinpractice.view.activity.MainActivity;
import com.example.finalprojectinpractice.view.activity.detail.detailType.DetailHospitalActivity;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAllAgencies;

import java.util.ArrayList;

public class HospitalAgencyActivity extends AppCompatActivity {

    private HospitalAdapter hospitalAdapter;
    private ViewModelAllAgencies viewModelAllAgencies;
    private RecyclerView recyclerViewHospital;
    private EditText searchHospital;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(HospitalAgencyActivity.this, MainActivity.class);
        intent.putExtra("Check", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_agency);

        recyclerViewHospital = findViewById(R.id.hospitalActivity_RecyclerView);
        recyclerViewHospital.setLayoutManager(new LinearLayoutManager(this));

        hospitalAdapter = new HospitalAdapter(this);
        hospitalAdapter.notifyDataSetChanged();

        recyclerViewHospital.setAdapter(hospitalAdapter);

        viewModelAllAgencies = new ViewModelProvider(this).get(ViewModelAllAgencies.class);
        viewModelAllAgencies.setListAllAgencies();
        viewModelAllAgencies.getListAllAgencies().observe(this, getAllAgenciesViewModel);

        searchHospital = findViewById(R.id.hospitalActivity_searchBar);

        searchHospital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hospitalAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private Observer<ArrayList<ModelAllAgenciesResultItem>> getAllAgenciesViewModel= new Observer<ArrayList<ModelAllAgenciesResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAllAgenciesResultItem> modelAllAgenciesResultItems) {
            hospitalAdapter.setData(modelAllAgenciesResultItems);
            hospitalAdapter.setListener(new HospitalAdapter.ListenerHospital() {
                @Override
                public void onClick(int position) {
                    ModelAllAgenciesResultItem agencies = modelAllAgenciesResultItems.get(position);
                    goToDetail(agencies);
                }
            });
        }
    };

    private void goToDetail(ModelAllAgenciesResultItem agencies) {
        Intent intent = new Intent(this, DetailHospitalActivity.class);
        intent.putExtra(DetailHospitalActivity.EXTRA_AGENCY_ID_HOSPITAL, agencies);
        startActivity(intent);
    }

}
