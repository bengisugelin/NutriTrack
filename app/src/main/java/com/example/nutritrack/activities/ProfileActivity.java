package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nutritrack.R;
import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.repository.DatabaseHeper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {


    TextView profileName;
    TextView profileemail;
    TextView profileUsername, profileDob;
    EditText profilePassword, profileEmail, profileHeight, profileWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        //BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        profileName = findViewById(R.id.txtprofile_nameInfo);
        profileemail = findViewById(R.id.txtprofile_email);
        profileUsername = findViewById(R.id.txt_profileUsername);
        profileDob = findViewById(R.id.profileDOB);
        profilePassword = findViewById(R.id.editTxt_profile_password);
        profileEmail = findViewById(R.id.editText_email);
        profileHeight = findViewById(R.id.editText_height);
        profileWeight = findViewById(R.id.edittext_profileweight);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        Intent goTohomePage = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(goTohomePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(ProfileActivity.this, LogActivity.class);
                        startActivity(goToLogPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.discover:
                        Intent goToDiscoverPage = new Intent(ProfileActivity.this, DiscoverActivity.class);
                        startActivity(goToDiscoverPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;
            }
        });

        DatabaseHeper databaseHelper = new DatabaseHeper(ProfileActivity.this);
        List<UserModel> userInfo = databaseHelper.getAllData("admin");



        for (int i = 0; i<userInfo.size(); i++) {
            profileName.setText(userInfo.get(i).getFname() + " " + userInfo.get(i).getLname());
            profileemail.setText(userInfo.get(i).getEmail());
            profileUsername.setText(userInfo.get(i).getUsername());
            profileDob.setText(userInfo.get(i).getDob());
            profilePassword.setText(userInfo.get(i).getPassword());
            profileEmail.setText(userInfo.get(i).getEmail());
            profileHeight.setText(Double.toString(userInfo.get(i).getHeight()));
            profileWeight.setText(Double.toString(userInfo.get(i).getWeight()));
        }
    }
}