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
import com.example.finalprojectinpractice.adapter.typeAgencyAdapter.HospitalAdapter;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import com.example.finalprojectinpractice.view.activity.MainActivity;

import java.util.ArrayList;

public class DetailHospitalActivity extends AppCompatActivity {

    public static final String EXTRA_AGENCY_ID_HOSPITAL = "";
    private TextView tvAgencyName, tvAgencyType, tvAgencyPhone, tvAgencyAddress, tvAgencyCity, tvAgencyLatitude, tvAgencyLongitude;
    private Button btnCall, btnPlace;
    private ModelAllAgenciesResultItem agency;
    private ArrayList<ModelAllAgenciesResultItem> modelHospital = new ArrayList<>();

    private String numberTelp = "";
    private static final int PHONE_REQUEST_CODE = 986;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        tvAgencyName = findViewById(R.id.detailHospital_AgencyName);
        tvAgencyType = findViewById(R.id.detailHospital_AgencyType);
        tvAgencyPhone = findViewById(R.id.detailHospital_AgencyPhoneNumber);
        tvAgencyAddress = findViewById(R.id.detailHospital_AgencyAddress);
        tvAgencyCity = findViewById(R.id.detailHospital_AgencyCity);
        tvAgencyLatitude = findViewById(R.id.detailHospital_AgencyLatitude);
        tvAgencyLongitude = findViewById(R.id.detailHospital_AgencyLongitude);

        btnCall = findViewById(R.id.detailHospital_ButtonCall);
        btnPlace = findViewById(R.id.detailHospital_ButtonLocation);

        agency = (ModelAllAgenciesResultItem) getIntent().getExtras().get(EXTRA_AGENCY_ID_HOSPITAL);

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
        Intent intent = new Intent(DetailHospitalActivity.this, MainActivity.class);
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

    //void untuk menyesuaikan id yang diterima dari intent dengan id yang berada di array list hospital
    private void setDataDetail(String id) {
        modelHospital = HospitalAdapter.modelHospitalFiltered;
        for (int i=0; i<= modelHospital.size(); i++) {
            int j = i;
            if (Integer.parseInt(id) <= 28) {
                if (j == Integer.parseInt(id)-1) {
                    //int j = i - 1; //karena id yang diterima dari intent dimulai dari 1 sedangkan index array list dimulai dari 0
                    tvAgencyName.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaInstansi());
                    tvAgencyType.setText(HospitalAdapter.modelHospitalFiltered.get(j).getJenisInstansi());
                    tvAgencyPhone.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNomorInstansi());
                    tvAgencyAddress.setText(HospitalAdapter.modelHospitalFiltered.get(j).getAlamatInstansi());
                    tvAgencyCity.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaKabupaten());
                    tvAgencyLatitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLat());
                    tvAgencyLongitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLong());
                }
            }
            else if (Integer.parseInt(id) > 28 && Integer.parseInt(id) <= 87 ){
                // belum tau penyebabnya, id 29 selalu tidak ada / dilewati, langsung ke id 30
                if (j == Integer.parseInt(id)-2) {
                    //int j = i - 2; //karena id yang diterima dari intent dimulai dari 1 sedangkan index array list dimulai dari 0
                    tvAgencyName.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaInstansi());
                    tvAgencyType.setText(HospitalAdapter.modelHospitalFiltered.get(j).getJenisInstansi());
                    tvAgencyPhone.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNomorInstansi());
                    tvAgencyAddress.setText(HospitalAdapter.modelHospitalFiltered.get(j).getAlamatInstansi());
                    tvAgencyCity.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaKabupaten());
                    tvAgencyLatitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLat());
                    tvAgencyLongitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLong());
                }
            }
            else if (Integer.parseInt(id) > 88 && Integer.parseInt(id) <= 109){
                // belum tau penyebabnya, id 110 selalu tidak ada / dilewati, langsung ke id 89
                if (j == Integer.parseInt(id)-3) {
                    //int j = i - 3; //karena id yang diterima dari intent dimulai dari 1 sedangkan index array list dimulai dari 0
                    tvAgencyName.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaInstansi());
                    tvAgencyType.setText(HospitalAdapter.modelHospitalFiltered.get(j).getJenisInstansi());
                    tvAgencyPhone.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNomorInstansi());
                    tvAgencyAddress.setText(HospitalAdapter.modelHospitalFiltered.get(j).getAlamatInstansi());
                    tvAgencyCity.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaKabupaten());
                    tvAgencyLatitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLat());
                    tvAgencyLongitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLong());
                }
            }
            else if (Integer.parseInt(id) > 109){
                // belum tau penyebabnya, id 110 selalu tidak ada / dilewati, langsung ke id 111
                if (j == Integer.parseInt(id)-4) {
                    //int j = i - 4; //karena id yang diterima dari intent dimulai dari 1 sedangkan index array list dimulai dari 0
                    tvAgencyName.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaInstansi());
                    tvAgencyType.setText(HospitalAdapter.modelHospitalFiltered.get(j).getJenisInstansi());
                    tvAgencyPhone.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNomorInstansi());
                    tvAgencyAddress.setText(HospitalAdapter.modelHospitalFiltered.get(j).getAlamatInstansi());
                    tvAgencyCity.setText(HospitalAdapter.modelHospitalFiltered.get(j).getNamaKabupaten());
                    tvAgencyLatitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLat());
                    tvAgencyLongitude.setText(HospitalAdapter.modelHospitalFiltered.get(j).getLong());
                }
            }
        }
    }


}
