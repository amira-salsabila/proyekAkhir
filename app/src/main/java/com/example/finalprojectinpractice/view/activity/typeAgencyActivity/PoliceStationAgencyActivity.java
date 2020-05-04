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
import com.example.finalprojectinpractice.adapter.typeAgencyAdapter.PoliceStationAdapter;
import com.example.finalprojectinpractice.database.UserModel;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import com.example.finalprojectinpractice.view.activity.MainActivity;
import com.example.finalprojectinpractice.view.activity.detail.detailType.DetailPoliceStationActivity;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAllAgencies;

import java.util.ArrayList;

public class PoliceStationAgencyActivity extends AppCompatActivity {

    private PoliceStationAdapter policeStationAdapter;
    private ViewModelAllAgencies viewModelAllAgencies;
    private RecyclerView recyclerViewPoliceStation;
    private EditText searchPoliceStation;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PoliceStationAgencyActivity.this, MainActivity.class);
        intent.putExtra("Check", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_station);

        recyclerViewPoliceStation = findViewById(R.id.policeStationActivity_RecyclerView);
        recyclerViewPoliceStation.setLayoutManager(new LinearLayoutManager(this));

        policeStationAdapter = new PoliceStationAdapter(this);
        policeStationAdapter.notifyDataSetChanged();

        recyclerViewPoliceStation.setAdapter(policeStationAdapter);

        viewModelAllAgencies = new ViewModelProvider(this).get(ViewModelAllAgencies.class);
        viewModelAllAgencies.setListAllAgencies();
        viewModelAllAgencies.getListAllAgencies().observe(this, getAllAgenciesViewModel);

        searchPoliceStation = findViewById(R.id.policeStationActivity_searchBar);
        searchPoliceStation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                policeStationAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAllAgenciesResultItem>> getAllAgenciesViewModel = new Observer<ArrayList<ModelAllAgenciesResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAllAgenciesResultItem> modelAllAgenciesResultItems) {
            policeStationAdapter.setData(modelAllAgenciesResultItems);

            policeStationAdapter.setListener(new PoliceStationAdapter.ListenerPoliceStation() {
                @Override
                public void onClick(int position) {
                    ModelAllAgenciesResultItem agencies = modelAllAgenciesResultItems.get(position);
                    goToDetail(agencies);
                }
            });
        }
    };

    private void goToDetail(ModelAllAgenciesResultItem agencies) {
        Intent intent = new Intent(this, DetailPoliceStationActivity.class);
        intent.putExtra(DetailPoliceStationActivity.EXTRA_AGENCY_ID_POLICE_STATION, agencies);
        startActivity(intent);
    }
}
