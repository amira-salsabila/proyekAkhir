package com.example.finalprojectinpractice.view.activity.detail.detailCity;

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
import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResultItem;
import com.example.finalprojectinpractice.view.activity.MainActivity;

public class DetailAgencyJakartaSelatan extends AppCompatActivity {

    public static final String EXTRA_AGENCY_ID_JAKARTA_SELATAN= "";

    private TextView tvAgencyName, tvAgencyType, tvAgencyPhone, tvAgencyAddress, tvAgencyCity, tvAgencyLatitude, tvAgencyLongitude;
    private Button btnCall, btnPlace;
    ModelAgencyJakartaSelatanResultItem agency;

    private String numberTelp = "";
    private static final int PHONE_REQUEST_CODE = 986;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agency_jakarta_selatan);

        tvAgencyName = findViewById(R.id.detailJakartaSelatan_AgencyName);
        tvAgencyType = findViewById(R.id.detailJakartaSelatan_AgencyType);
        tvAgencyPhone = findViewById(R.id.detailJakartaSelatan_AgencyPhoneNumber);
        tvAgencyAddress = findViewById(R.id.detailJakartaSelatan_AgencyAddress);
        tvAgencyCity = findViewById(R.id.detailJakartaSelatan_AgencyCity);
        tvAgencyLatitude = findViewById(R.id.detailJakartaSelatan_AgencyLatitude);
        tvAgencyLongitude = findViewById(R.id.detailJakartaSelatan_AgencyLongitude);

        btnCall = findViewById(R.id.detailJakartaSelatan_ButtonCall);
        btnPlace =findViewById(R.id.detailJakartaSelatan_ButtonLocation);

        agency = (ModelAgencyJakartaSelatanResultItem) getIntent().getExtras().get(EXTRA_AGENCY_ID_JAKARTA_SELATAN);

        tvAgencyName.setText(agency.getNamaInstansi());
        tvAgencyType.setText(agency.getJenisInstansi());
        tvAgencyPhone.setText(agency.getNomorInstansi());
        tvAgencyAddress.setText(agency.getAlamatInstansi());
        tvAgencyCity.setText(agency.getNamaKabupaten());
        tvAgencyLatitude.setText(agency.getLat());
        tvAgencyLongitude.setText(agency.getLong());

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
        Intent intent = new Intent(DetailAgencyJakartaSelatan.this, MainActivity.class);
        intent.putExtra("Check", 3);
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
}
