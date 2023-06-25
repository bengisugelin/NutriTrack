package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nutritrack.adapters.C_RecyclerViewAdapter;
import com.example.nutritrack.models.ConsumptionModel;
import com.example.nutritrack.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView showAmountcalorieLeft;
    ArrayList<ConsumptionModel> consumptionModels = new ArrayList<>();
    int [] consumptionImages = {R.drawable.baseline_breakfast_24,
            R.drawable.baseline_food_bank_24,
    R.drawable.baseline_dinner_dining_24,
    R.drawable.baseline_snack_24,
    R.drawable.baseline_fitness_center_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_addconsumption);

        setupConsumptionItems();

        C_RecyclerViewAdapter adapter = new C_RecyclerViewAdapter(this, consumptionModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        showAmountcalorieLeft = findViewById(R.id.textShowCalorieLeft);
        showAmountcalorieLeft.setText("1774");
    }

    private void setupConsumptionItems(){

         String[] consumptionNames = getResources().getStringArray(R.array.eat_and_burn_times);

         for(int i=0; i<consumptionNames.length; i++){
             consumptionModels.add(new ConsumptionModel(consumptionNames[i],
                     consumptionImages[i]));

         }
    }
}