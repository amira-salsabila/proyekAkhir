package com.example.finalprojectinpractice.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.database.UserModel;
import com.example.finalprojectinpractice.view.fragment.BandungFragment;
import com.example.finalprojectinpractice.view.fragment.HomeFragment;
import com.example.finalprojectinpractice.view.fragment.JakartaBaratFragment;
import com.example.finalprojectinpractice.view.fragment.JakartaSelatanFragment;
import com.example.finalprojectinpractice.view.fragment.JakartaTimurFragment;
import com.example.finalprojectinpractice.view.fragment.JakartaUtaraFragment;
import com.example.finalprojectinpractice.view.fragment.ProfileFragment;
import com.example.finalprojectinpractice.view.fragment.PurwakartaFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static UserModel userModel;
    private DrawerLayout activitymainDrawerLayout;
    private ActionBarDrawerToggle activitymainDrawerToogle;
    private Fragment selectedFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activitymainDrawerLayout = findViewById(R.id.activitymain_drawerlayout);
        activitymainDrawerToogle = new ActionBarDrawerToggle(this, activitymainDrawerLayout, R.string.toogle_nav_open, R.string.toogle_nav_close);
        activitymainDrawerLayout.addDrawerListener(activitymainDrawerToogle);
        activitymainDrawerToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView activitymainNavigationView = (NavigationView) findViewById(R.id.activitymain_navigationView);
        setupDrawerContent(activitymainNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();

        userModel = (UserModel) getIntent().getSerializableExtra("UserSession");

        //back button
        Intent intent = getIntent();
        String intentCode = "";

        if (intentCode.equals("0")) {
            selectedFragment = new BandungFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("1")) {
            selectedFragment = new BandungFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("2")) {
            selectedFragment = new JakartaBaratFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("3")) {
            selectedFragment = new JakartaSelatanFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("4")) {
            selectedFragment = new JakartaTimurFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("5")) {
            selectedFragment = new JakartaUtaraFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("6")) {
            selectedFragment = new PurwakartaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        } else if (intentCode.equals("7")) {
            selectedFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(activitymainDrawerToogle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onOptionsSelectedDrawer(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_navigationView_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.menu_navigationView_KotaBandung:
                selectedFragment = new BandungFragment();
                break;
            case R.id.menu_navigationView_JakartaUtara:
                selectedFragment = new JakartaUtaraFragment();
                break;
            case R.id.menu_navigationView_JakartaSelatan:
                selectedFragment = new JakartaSelatanFragment();
                break;
            case R.id.menu_navigationView_JakartaTimur:
                selectedFragment = new JakartaTimurFragment();
                break;
            case R.id.menu_navigationView_JakartaBarat:
                selectedFragment = new JakartaBaratFragment();
                break;
            case R.id.menu_navigationView_KabPurwakarta:
                selectedFragment = new PurwakartaFragment();
                break;
            case R.id.menu_navigationView_profile:
                selectedFragment = new ProfileFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.activitymain_framecontainer, selectedFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        activitymainDrawerLayout.closeDrawers();

    }

   private void setupDrawerContent (NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                onOptionsSelectedDrawer(menuItem);
                return true;
            }
        });
   }

}
