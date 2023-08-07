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

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");

        //BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.log);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.log:
                        return true;
                    case R.id.profile:
                        Intent goToProfilePage = new Intent(LogActivity.this, ProfileActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("USERNAME", username);
                        goToProfilePage.putExtras(bundle);

                        startActivity(goToProfilePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.home:
                        Intent goToHomePage = new Intent(LogActivity.this, MainActivity.class);

                        Bundle bundle_home = new Bundle();
                        bundle_home.putString("USERNAME", username);
                        goToHomePage.putExtras(bundle_home);


                        startActivity(goToHomePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.discover:
                        Intent goToDiscoverPage = new Intent(LogActivity.this, DiscoverActivity.class);

                        Bundle bundle_dis = new Bundle();
                        bundle_dis.putString("USERNAME", username);
                        goToDiscoverPage.putExtras(bundle_dis);


                        startActivity(goToDiscoverPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;
            }
        });
    }
}