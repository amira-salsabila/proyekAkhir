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
import com.example.finalprojectinpractice.adapter.typeAgencyAdapter.FireStationAdapter;
import com.example.finalprojectinpractice.database.UserModel;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import com.example.finalprojectinpractice.view.activity.MainActivity;
import com.example.finalprojectinpractice.view.activity.detail.detailType.DetailFireStationActivity;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAllAgencies;

import java.util.ArrayList;

public class FireStationAgencyActivity extends AppCompatActivity {

    private FireStationAdapter fireStationAdapter;
    private ViewModelAllAgencies viewModelAllAgencies;
    private RecyclerView recyclerViewFireStation;
    private EditText searchFireStation;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FireStationAgencyActivity.this, MainActivity.class);
        intent.putExtra("Check", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_station_agency);

        recyclerViewFireStation = findViewById(R.id.fireStationActivity_RecyclerView);
        recyclerViewFireStation.setLayoutManager(new LinearLayoutManager(this));

        fireStationAdapter = new FireStationAdapter(this);
        fireStationAdapter.notifyDataSetChanged();

        recyclerViewFireStation.setAdapter(fireStationAdapter);

        viewModelAllAgencies = new ViewModelProvider(this).get(ViewModelAllAgencies.class);
        viewModelAllAgencies.setListAllAgencies();
        viewModelAllAgencies.getListAllAgencies().observe(this, getAllAgenciesViewModel);

        searchFireStation = findViewById(R.id.fireStationActivity_searchBar);
        searchFireStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fireStationAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAllAgenciesResultItem>> getAllAgenciesViewModel = new Observer<ArrayList<ModelAllAgenciesResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAllAgenciesResultItem> modelAllAgenciesResultItems) {
            fireStationAdapter.setData(modelAllAgenciesResultItems);

            fireStationAdapter.setListener(new FireStationAdapter.ListenerFireStation() {
                @Override
                public void onClick(int position) {
                    ModelAllAgenciesResultItem agencies = modelAllAgenciesResultItems.get(position);
                    goToDetail(agencies);
                }
            });
        }
    };

    private void goToDetail(ModelAllAgenciesResultItem agencies) {
        Intent intent = new Intent(this, DetailFireStationActivity.class);
        intent.putExtra(DetailFireStationActivity.EXTRA_AGENCY_ID_FIRE_STATION, agencies);
        startActivity(intent);
    }
}
