package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritrack.R;
import com.example.nutritrack.adapters.LogRecyclerViewAdapter;
import com.example.nutritrack.models.logModel;
import com.example.nutritrack.models.nutritionModel;
import com.example.nutritrack.repository.DatabaseHeper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {

    TextView pageTitle;

    ArrayList<logModel> logModelsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log);

        RecyclerView recyclerView = findViewById(R.id.log_recyclerview);
        setUpLogModels();

        LogRecyclerViewAdapter adapter = new LogRecyclerViewAdapter(this, logModelsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");


        pageTitle = findViewById(R.id.logpage_title);
        pageTitle.setText("Here is your intake, " + username +"!");



        DatabaseHeper NutriTrackdb = new DatabaseHeper(LogActivity.this);

        List<nutritionModel> nutritionList = NutriTrackdb.getAllNutritionData();

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


    private  void setUpLogModels(){

        DatabaseHeper NutriTrackdb = new DatabaseHeper(LogActivity.this);

        List<nutritionModel> nutritionList = NutriTrackdb.getAllNutritionData();


        for (int i=1; i<nutritionList.size(); i++){
            logModelsList.add(new logModel(nutritionList.get(i).getFood_name(), nutritionList.get(i).getCalories()));

        }
    }
}