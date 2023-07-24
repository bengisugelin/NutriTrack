package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nutritrack.adapters.C_RecyclerViewAdapter;
import com.example.nutritrack.adapters.C_RecyclerViewInterface;
import com.example.nutritrack.models.ConsumptionModel;
import com.example.nutritrack.R;
import com.example.nutritrack.models.nutritionModel;
import com.example.nutritrack.repository.DatabaseHeper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements C_RecyclerViewInterface {


    TextView showAmountcalorieLeft, caloriAmounteaten;
    ArrayList<ConsumptionModel> consumptionModels = new ArrayList<>();
    int [] consumptionImages = {R.drawable.baseline_breakfast_24,
            R.drawable.baseline_food_bank_24,
    R.drawable.baseline_dinner_dining_24,
    R.drawable.baseline_snack_24,
    R.drawable.baseline_fitness_center_24};

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_chooseconsumption);
        setupConsumptionItems();


        //create your adapter after you set up the components, otherwise it will be null.
        C_RecyclerViewAdapter adapter = new C_RecyclerViewAdapter(this, consumptionModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressBar = findViewById(R.id.progressBar);
        showAmountcalorieLeft = findViewById(R.id.textShowCalorieLeft);
        caloriAmounteaten = findViewById(R.id.textViewCalorieAmountEaten);


        DatabaseHeper databaseHeper = new DatabaseHeper(MainActivity.this);
        List<nutritionModel> nutritionList = databaseHeper.getAllNutritionData();

        double totalcalorieeaten = 0;



        for (int i = 0; i<nutritionList.size(); i++){

            totalcalorieeaten += nutritionList.get(i).getCalories();
            }

        double calculateCalorieRatio = (totalcalorieeaten / 1774.0 )*100;
        startAnimator(0, (int) calculateCalorieRatio);

        showAmountcalorieLeft.setText(Integer.toString(1774 - (int)totalcalorieeaten));
        caloriAmounteaten.setText(Double.toString(totalcalorieeaten));

        //BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        Intent goToProfilePage = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(goToProfilePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(MainActivity.this, LogActivity.class);
                        startActivity(goToLogPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.discover:
                        Intent goToDiscoverPage = new Intent(MainActivity.this, DiscoverActivity.class);
                        startActivity(goToDiscoverPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;
            }
        });
    }

    public void startAnimator(int start_no, int end_no){
        ValueAnimator animator = ValueAnimator.ofInt( start_no, end_no);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {

                progressBar.setProgress(Integer.parseInt(animator.getAnimatedValue().toString()));
            }

        });
        animator.start();
    }

    private void setupConsumptionItems(){

         String[] consumptionNames = getResources().getStringArray(R.array.eat_and_burn_times);

         for(int i=0; i<consumptionNames.length; i++){
             consumptionModels.add(new ConsumptionModel(consumptionNames[i],
                     consumptionImages[i]));

         }
    }

    @Override
    public void onItemClicked(int position) {

        Intent goTotheSearchActivity = new Intent(MainActivity.this, SearchActivity.class);

        getIntent().putExtra("NAME", consumptionModels.get(position).getConsumptionName());

        startActivity(goTotheSearchActivity);


    }
}