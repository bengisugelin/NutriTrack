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
import com.example.nutritrack.adapters.LogRecyclerViewInterface;
import com.example.nutritrack.models.logModel;
import com.example.nutritrack.models.nutritionModel;
import com.example.nutritrack.repository.DatabaseHeper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity implements LogRecyclerViewInterface {

    TextView pageTitle;
    TextView calculatedTotalCalories, totalProtein, totalCarbs, totalFat;

    ArrayList<logModel> logModelsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log);

        RecyclerView recyclerView = findViewById(R.id.log_recyclerview);
        setUpLogModels();

        LogRecyclerViewAdapter adapter = new LogRecyclerViewAdapter(this, logModelsList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");

        DatabaseHeper NutriTrackdb = new DatabaseHeper(LogActivity.this);

        List<nutritionModel> nutritionList = NutriTrackdb.getAllNutritionData();


        pageTitle = findViewById(R.id.logpage_title);
        pageTitle.setText("Here is your intake, " + username +"!");


        calculatedTotalCalories = findViewById(R.id.txt_TotalCaloriCalculated);
        totalProtein = findViewById(R.id.log_txt_totalProtein);
        totalCarbs = findViewById(R.id.log_txt_totalCarbs);
        totalFat = findViewById(R.id.log_txt_totalFat);


        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        //calculate total calories
        double tot_calories=0;

        for (int i=0; i<nutritionList.size(); i++){
            tot_calories+= nutritionList.get(i).getCalories();
        }

        String t_cal = decimalFormat.format(tot_calories);
        calculatedTotalCalories.setText( t_cal + " KCAL");

       // calculate total protein
        double tot_protein = 0;
        for (int i=0; i<nutritionList.size(); i++){
            tot_protein+= nutritionList.get(i).getProtein();
        }

        totalProtein.setText( decimalFormat.format(tot_protein) + " g");

        // calculate total carbs
        double tot_carbs = 0;
        for (int i=0; i<nutritionList.size(); i++){
            tot_carbs+= nutritionList.get(i).getTotal_carbonhydrate();
        }

        totalCarbs.setText(decimalFormat.format(tot_carbs) + " g");

        // calculate total fat
        double tot_fat = 0;
        for (int i=0; i<nutritionList.size(); i++){
            tot_fat+= nutritionList.get(i).getTotal_fat();
        }

        totalFat.setText(decimalFormat.format(tot_fat) + " g");






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


        for (int i=0; i<nutritionList.size(); i++){
            logModelsList.add(new logModel(nutritionList.get(i).getFood_name(), nutritionList.get(i).getCalories()));

        }
    }

    @Override
    public void onItemClick(int position) {


        DatabaseHeper nutritrack = new DatabaseHeper(LogActivity.this);
        List<nutritionModel> nutritionList = nutritrack.getAllNutritionData();
        nutritrack.deleteLog(nutritionList.get(position).getFood_name());
    }
}