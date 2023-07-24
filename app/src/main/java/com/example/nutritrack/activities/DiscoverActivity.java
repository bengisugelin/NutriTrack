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
                        startActivity(goToProfilePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.home:
                        Intent goToHomePage = new Intent(DiscoverActivity.this, MainActivity.class);
                        startActivity(goToHomePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(DiscoverActivity.this, LogActivity.class);
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