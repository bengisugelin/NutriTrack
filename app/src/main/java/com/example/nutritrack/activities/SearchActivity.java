package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutritrack.R;
import com.example.nutritrack.apis.VolleySingleton;
import com.example.nutritrack.models.nutritionModel;
import com.example.nutritrack.repository.DatabaseHeper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText SearchValue;

    TextView calorieAmount, totalfatAmount, saturatedFatAmount, cholesterolAmount, SodiumAmount,
            totalCarbonhydrateAmount, dietaryFiberAmount, totalSugarAmount, proteinAmount,
            calciumAmount, ironAmount, potassiumAmount, foodtitle;

    TextView calorieUnit, totalFatUnit, satFatUnit, cholesterolUnit, sodiumUnit, totalCarbsUnit,
    DietaryFiberUnit, totalSugarUnit, proteinUnit, calciumUnit, ironUnit, potassiumUnit;

    ImageButton btn_searchButton;
    Button btn_addNutrition;
    String searchedFood;



    private RequestQueue requestQueue;
    private ArrayList<nutritionModel> nutritionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);

        String name = getIntent().getStringExtra("NAME");

        SearchValue = findViewById(R.id.editText_searchedfood);
        calorieAmount = findViewById(R.id.txt_calorieAmount);
        totalfatAmount = findViewById(R.id.text_totalFatAmount);
        saturatedFatAmount = findViewById(R.id.text_SaturatedFatAmount);
        cholesterolAmount = findViewById(R.id.text_CholesterolAmount);
        SodiumAmount = findViewById(R.id.text_SodiumAmount);
        totalCarbonhydrateAmount = findViewById(R.id.text_TotalCarbohydrateAmount);
        dietaryFiberAmount = findViewById(R.id.text_DietaryFiberAmount);
        totalSugarAmount = findViewById(R.id.text_TotalSugarsAmount);
        proteinAmount = findViewById(R.id.txt_ProteinAmount);
        calciumAmount = findViewById(R.id.text_CalciumAmount);
        ironAmount = findViewById(R.id.text_IronAmount);
        potassiumAmount = findViewById(R.id.text_PotassiumAmount);
        foodtitle = findViewById(R.id.txtsearchedFood);
        btn_searchButton = findViewById(R.id.searchButton);
        btn_addNutrition = findViewById(R.id.btn_addNutrition);

        //units
        calorieUnit =findViewById(R.id.txt_calorieUnit);
        totalFatUnit =findViewById(R.id.txt_totalFatUnit);
        satFatUnit =findViewById(R.id.txt_saturatedtFatUnit);
        cholesterolUnit =findViewById(R.id.txt_cholesterolUnit);
        sodiumUnit =findViewById(R.id.txt_sodiumUnit);
        totalCarbsUnit =findViewById(R.id.txt_totalCarbsUnit);
        DietaryFiberUnit =findViewById(R.id.txt_dietaryFiberUnit);
        totalSugarUnit =findViewById(R.id.txt_totalSugarUnit);
        proteinUnit =findViewById(R.id.txt_proteinUnit);
        calciumUnit =findViewById(R.id.txt_calciumUnit);
        ironUnit =findViewById(R.id.txt_ironUnit);
        potassiumUnit =findViewById(R.id.txt_potassiumUnit);


        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        nutritionList = new ArrayList<>();

        SearchValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                searchedFood = SearchValue.getText().toString();
                foodtitle.setText(searchedFood);
            }
        });

        btn_searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DecimalFormat decimalFormat = new DecimalFormat("#.##");

                String foodnametoinserttoURL = searchedFood.replace(" ", "%20").toLowerCase();

                //Toast.makeText(SearchActivity.this, foodnametoinserttoURL, Toast.LENGTH_SHORT).show();

                String url = "https://api.edamam.com/api/nutrition-data?app_id=e0d93112&app_key=ae1aa249b69cad6d6b3aff0ca4ea61b1&nutrition-type=cooking&ingr=" + foodnametoinserttoURL;

//                String url = "https://api.edamam.com/api/nutrition-data?app_id=e0d93112&app_key=ae1aa249b69cad6d6b3aff0ca4ea61b1&nutrition-type=cooking&ingr=a%20cup%20of%20coffee";

                //---------fetch nutritions begins-----------------------
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<String> keys = new ArrayList<>();
                            keys.add("ENERC_KCAL");
                            keys.add("FAT");
                            keys.add("FASAT");
                            keys.add("CHOLE");
                            keys.add("NA");
                            keys.add("CHOCDF");
                            keys.add("FIBTG");
                            keys.add("SUGAR");
                            keys.add("PROCNT");
                            keys.add("CA");
                            keys.add("FE");
                            keys.add("K");

                            ArrayList<TextView> texviewnames = new ArrayList<>();
                            texviewnames.add(calorieAmount);
                            texviewnames.add(totalfatAmount);
                            texviewnames.add(saturatedFatAmount);
                            texviewnames.add(cholesterolAmount);
                            texviewnames.add(SodiumAmount);
                            texviewnames.add(totalCarbonhydrateAmount);
                            texviewnames.add(dietaryFiberAmount);
                            texviewnames.add(totalSugarAmount);
                            texviewnames.add(proteinAmount);
                            texviewnames.add(calciumAmount);
                            texviewnames.add(ironAmount);
                            texviewnames.add(potassiumAmount);

                            ArrayList<TextView> units = new ArrayList<>();
                            units.add(calorieUnit);
                            units.add(totalFatUnit);
                            units.add(satFatUnit);
                            units.add(cholesterolUnit);
                            units.add(sodiumUnit);
                            units.add(totalCarbsUnit);
                            units.add(DietaryFiberUnit);
                            units.add(totalSugarUnit);
                            units.add(proteinUnit);
                            units.add(calciumUnit);
                            units.add(ironUnit);
                            units.add(potassiumUnit);


                            for (int i = 0; i<keys.size(); i++){
                                JSONObject obj = response.getJSONObject("totalNutrients").getJSONObject(keys.get(i));
                                double quantity = obj.getDouble("quantity");
                                String unit = obj.getString("unit");

                                String formatAmount = decimalFormat.format(quantity);
                                texviewnames.get(i).setText(formatAmount);
                                units.get(i).setText(unit);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle API call failure
                            }
                        }
                );

                RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this); // 'this' refers to the current context
                requestQueue.add(jsonObjectRequest);
                //---------fetch nutritions ends-----------------------


            }

        });


        // String url = "https://api.edamam.com/api/nutrition-data?app_id=e0d93112&app_key=ae1aa249b69cad6d6b3aff0ca4ea61b1&nutrition-type=cooking&ingr=a%20cup%20of%20coffee";


        btn_addNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nutritionModel nutritionModel = new nutritionModel(
                        SearchValue.getText().toString().trim(),
                        Double.parseDouble(calorieAmount.getText().toString().trim()),
                        Double.parseDouble(totalfatAmount.getText().toString().trim()),
                        Double.parseDouble(saturatedFatAmount.getText().toString().trim()),
                        Double.parseDouble(cholesterolAmount.getText().toString().trim()),
                        Double.parseDouble(SodiumAmount.getText().toString().trim()),
                        Double.parseDouble(totalCarbonhydrateAmount.getText().toString().trim()),
                        Double.parseDouble(dietaryFiberAmount.getText().toString().trim()),
                        Double.parseDouble( totalSugarAmount.getText().toString().trim()),
                        Double.parseDouble( proteinAmount.getText().toString().trim()),
                        Double.parseDouble( calciumAmount.getText().toString().trim()),
                        Double.parseDouble(ironAmount.getText().toString().trim()),
                        Double.parseDouble( potassiumAmount.getText().toString().trim()));



                //database helper
                DatabaseHeper NutriTrackdb = new DatabaseHeper(SearchActivity.this);
//                boolean success = NutriTrackdb.addNutrition(nutritionModel);
                NutriTrackdb.addNutrition(nutritionModel);

                Toast.makeText(SearchActivity.this, "Success= ", Toast.LENGTH_SHORT).show();

                Intent goToMainpage = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(goToMainpage);

            }
        });


    }

}