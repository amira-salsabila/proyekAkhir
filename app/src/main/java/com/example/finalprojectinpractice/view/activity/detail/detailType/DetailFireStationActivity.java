package com.example.finalprojectinpractice.view.activity.detail.detailType;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.adapter.typeAgencyAdapter.FireStationAdapter;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import com.example.finalprojectinpractice.view.activity.MainActivity;

import java.util.ArrayList;

public class DetailFireStationActivity extends AppCompatActivity {

    public static final String EXTRA_AGENCY_ID_FIRE_STATION = "";
    private TextView tvAgencyName, tvAgencyType, tvAgencyPhone, tvAgencyAddress, tvAgencyCity, tvAgencyLatitude, tvAgencyLongitude;
    private Button btnCall, btnPlace;
    private ModelAllAgenciesResultItem agency;
    private ArrayList<ModelAllAgenciesResultItem> modelFireStation = new ArrayList<>();

    private String numberTelp = "";
    private static final int PHONE_REQUEST_CODE = 986;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fire_station);

        tvAgencyName = findViewById(R.id.detailFireStation_AgencyName);
        tvAgencyType = findViewById(R.id.detailFireStation_AgencyType);
        tvAgencyPhone = findViewById(R.id.detailFireStation_AgencyPhoneNumber);
        tvAgencyAddress = findViewById(R.id.detailFireStation_AgencyAddress);
        tvAgencyCity = findViewById(R.id.detailFireStation_AgencyCity);
        tvAgencyLatitude = findViewById(R.id.detailFireStation_AgencyLatitude);
        tvAgencyLongitude = findViewById(R.id.detailFireStation_AgencyLongitude);

        btnCall = findViewById(R.id.detailFireStation_ButtonCall);
        btnPlace = findViewById(R.id.detailFireStation_ButtonLocation);

        agency = (ModelAllAgenciesResultItem) getIntent().getExtras().get(EXTRA_AGENCY_ID_FIRE_STATION);

        String id = agency.getId();

        //tvAgencyType.setText(id);

        setDataDetail(id);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calling();
            }
        });

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocation();
            }
        });
    }

    private void calling(){
        numberTelp = tvAgencyPhone.getText().toString();
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numberTelp));
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, PHONE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PHONE_REQUEST_CODE){
            calling();
        } else {
            Toast.makeText(getApplicationContext(), "Aplikasi tidak diizinkan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetailFireStationActivity.this, MainActivity.class);
        intent.putExtra("Check", 1);
    }

    private void searchLocation() {
        String latitude = tvAgencyLatitude.getText().toString();
        String longitude = tvAgencyLongitude.getText().toString();
        //String type = tvAgencyType.getText().toString();
        //String address = tvAgencyAddress.getText().toString();
        String agencyName = tvAgencyName.getText().toString();
        /*if (type.equals("Rumkit")) {
            type = "rumah+sakit";
        } else if (type.equals("Polisi")) {
            type = "kantor+polisi";
        } else if (type.equals("Pemadam")) {
            type = "pemadam+kebakaran";
        } else {
            type="";
        }**/
        Uri gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude+"?z=30&q="+agencyName);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setData(gmmIntentUri);
        mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(mapIntent);
    }

    //void untuk menyesuaikan id yang diterima dari intent dengan id yang berada di array list fire station
    private void setDataDetail(String id) {
        modelFireStation = FireStationAdapter.modelFireStationFiltered;
        for (int i=0; i<= modelFireStation.size(); i++) {
            int j = i;
                if (j == Integer.parseInt(id)-1) {
                    //int j = i - 1; //karena id yang diterima dari intent dimulai dari 1 sedangkan index array list dimulai dari 0
                    tvAgencyName.setText(FireStationAdapter.modelFireStationFiltered.get(j).getNamaInstansi());
                    tvAgencyType.setText(FireStationAdapter.modelFireStationFiltered.get(j).getJenisInstansi());
                    tvAgencyPhone.setText(FireStationAdapter.modelFireStationFiltered.get(j).getNomorInstansi());
                    tvAgencyAddress.setText(FireStationAdapter.modelFireStationFiltered.get(j).getAlamatInstansi());
                    tvAgencyCity.setText(FireStationAdapter.modelFireStationFiltered.get(j).getNamaKabupaten());
                    tvAgencyLatitude.setText(FireStationAdapter.modelFireStationFiltered.get(j).getLat());
                    tvAgencyLongitude.setText(FireStationAdapter.modelFireStationFiltered.get(j).getLong());
                }
        }
    }
}
