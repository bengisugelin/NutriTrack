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
import android.widget.Toast;

import com.example.nutritrack.adapters.C_RecyclerViewAdapter;
import com.example.nutritrack.adapters.C_RecyclerViewInterface;
import com.example.nutritrack.models.ConsumptionModel;
import com.example.nutritrack.R;
import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.models.nutritionModel;
import com.example.nutritrack.repository.DatabaseHeper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    DatabaseHeper NutriTrackdb = new DatabaseHeper(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);



        //to import the username to the new hours page
        try {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("USERNAME", "user");
        }catch (Exception e){
            e.printStackTrace();
        }



        //Toast.makeText(this, "Welcome" + username, Toast.LENGTH_SHORT).show();



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

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");
        double totalcalorieIntake = calculateDailyCalorieIntake(username);

        double totalcalorieeaten = 0;



        for (int i = 0; i<nutritionList.size(); i++){

            totalcalorieeaten += nutritionList.get(i).getCalories();
            }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double calculateCalorieRatio = (totalcalorieeaten / totalcalorieIntake )*100;
        startAnimator(0, (int) calculateCalorieRatio);

        String formatAmount = decimalFormat.format(totalcalorieeaten);
        showAmountcalorieLeft.setText(Integer.toString((int)totalcalorieIntake - (int)totalcalorieeaten));
        caloriAmounteaten.setText(formatAmount);



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
                        //to export the username to the profile page
                        Bundle bundle = new Bundle();

                        bundle.putString("USERNAME", username);
                        goToProfilePage.putExtras(bundle);
                        startActivity(goToProfilePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(MainActivity.this, LogActivity.class);


                        Bundle bundle_log = new Bundle();
                        bundle_log.putString("USERNAME", username);
                        goToLogPage.putExtras(bundle_log);

                        startActivity(goToLogPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.discover:
                        Intent goToDiscoverPage = new Intent(MainActivity.this, DiscoverActivity.class);

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

        if(position == 4){

            Intent goToTheSearchExerciseActivity = new Intent(MainActivity.this, SearchExerciseActivity.class);
            startActivity(goToTheSearchExerciseActivity);

        }else{
            Intent goTotheSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(goTotheSearchActivity);
        }

        getIntent().putExtra("NAME", consumptionModels.get(position).getConsumptionName());

    }


    private double calculateDailyCalorieIntake(String username){

       // String username = "juliana";

        List<UserModel> userList =  NutriTrackdb.getAllData(username);

        double BMR = 0;
        double totalIntake = 0;

        for (int i = 0; i < userList.size(); i++){
            String dob = userList.get(i).getDob();
            if (userList.get(i).getSex().equals("male")){
                BMR = 66.5 + (13.75 * userList.get(i).getWeight()) + (5.003 * userList.get(i).getHeight()) - (6.75 * getAge(dob));
            }else if(userList.get(i).getSex().equals("female")){
                BMR = 655.1 + (9.563 * userList.get(i).getWeight() ) + (1.850 * userList.get(i).getHeight()) - (4.676 * getAge(dob));
            }

            if(userList.get(i).getActivityLevel().equals("sedentary")){
                totalIntake = BMR*1.2;
            } else if (userList.get(i).getActivityLevel().equals("lightly active")) {
                totalIntake = BMR*1.375;
            } else if (userList.get(i).getActivityLevel().equals("moderately active")) {
                totalIntake = BMR*1.55;
            }else if (userList.get(i).getActivityLevel().equals("very active")) {
                totalIntake = BMR*1.725;
            }else if (userList.get(i).getActivityLevel().equals("extra active")) {
                totalIntake = BMR*1.9;
            }
        }



        return totalIntake;
    }

    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }



        return age;
    }
}