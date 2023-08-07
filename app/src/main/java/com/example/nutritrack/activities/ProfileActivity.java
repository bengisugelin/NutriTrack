package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nutritrack.R;
import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.repository.DatabaseHeper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {


    TextView profileName, sex;
    TextView profileemail;
    TextView profileUsername, profileDob;
    EditText profilePassword, profileEmail, profileHeight, profileWeight;

    Spinner activitylevel;

    Button updateProfileInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");

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
        activitylevel=findViewById(R.id.spinner_activitylevel_profile);
        updateProfileInfo = findViewById(R.id.button_updateProfile);
        sex = findViewById(R.id.txt_profilesex);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        Intent goTohomePage = new Intent(ProfileActivity.this, MainActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("USERNAME", username);
                        goTohomePage.putExtras(bundle);


                        startActivity(goTohomePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(ProfileActivity.this, LogActivity.class);
                        Bundle bundle_log = new Bundle();
                        bundle_log.putString("USERNAME", username);
                        goToLogPage.putExtras(bundle_log);
                        startActivity(goToLogPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.discover:
                        Intent goToDiscoverPage = new Intent(ProfileActivity.this, DiscoverActivity.class);
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

        DatabaseHeper databaseHelper = new DatabaseHeper(ProfileActivity.this);
        List<UserModel> userInfo = databaseHelper.getAllData(username);



        for (int i = 0; i<userInfo.size(); i++) {
            if(userInfo.get(i).getUsername().toString().equals(username)){
            profileName.setText(userInfo.get(i).getFname() + " " + userInfo.get(i).getLname());
            profileemail.setText(userInfo.get(i).getEmail());
            profileUsername.setText(userInfo.get(i).getUsername());
            profileDob.setText(userInfo.get(i).getDob());
            profilePassword.setText(userInfo.get(i).getPassword());
            sex.setText(userInfo.get(i).getSex());
            profileEmail.setText(userInfo.get(i).getEmail());
            profileHeight.setText(Double.toString(userInfo.get(i).getHeight()));
            profileWeight.setText(Double.toString(userInfo.get(i).getWeight()));
            activitylevel.setPrompt(userInfo.get(i).getActivityLevel());

            }
        }


        updateProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String upd_profileName =  profileName.getText().toString();

               String fullname [] = upd_profileName.split(" ");


               String upd_firstname = fullname[0];
               String upd_lastname = fullname[1];

               String upd_profileUsername = profileUsername.getText().toString();
               String upd_profileDob = profileDob.getText().toString();
               String upd_profilePassword = profilePassword.getText().toString();
               String upd_sex = sex.getText().toString();
               String upd_profileEmail = profileEmail.getText().toString();
               Double upd_profileHeight = Double.parseDouble(profileHeight.getText().toString());
               Double upd_profileWeight = Double.parseDouble(profileWeight.getText().toString());
               String upd_activitylevel = activitylevel.getSelectedItem().toString();

               databaseHelper.updateProfileData(upd_firstname, upd_lastname, upd_profileUsername,
                       upd_profilePassword, upd_sex,upd_profileDob,upd_profileEmail,
                       upd_profileHeight, upd_profileWeight,upd_activitylevel);
            }
        });
    }
}