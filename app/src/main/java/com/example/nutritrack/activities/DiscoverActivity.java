package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.nutritrack.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_discover);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");

        //BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.discover);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.discover:
                        return true;
                    case R.id.profile:
                        Intent goToProfilePage = new Intent(DiscoverActivity.this, ProfileActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("USERNAME", username);
                        goToProfilePage.putExtras(bundle);
                        startActivity(goToProfilePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.home:
                        Intent goToHomePage = new Intent(DiscoverActivity.this, MainActivity.class);
                        Bundle bundle_home = new Bundle();
                        bundle_home.putString("USERNAME", username);
                        goToHomePage.putExtras(bundle_home);
                        startActivity(goToHomePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(DiscoverActivity.this, LogActivity.class);
                        Bundle bundle_log = new Bundle();
                        bundle_log.putString("USERNAME", username);
                        goToLogPage.putExtras(bundle_log);
                        startActivity(goToLogPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;
            }
        });
    }
}