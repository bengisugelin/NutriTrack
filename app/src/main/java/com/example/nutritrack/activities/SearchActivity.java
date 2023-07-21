package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutritrack.R;
import com.example.nutritrack.apis.VolleySingleton;
import com.example.nutritrack.models.nutritionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText SearchValue;

    TextView calorieAmount, totalfatAmount, saturatedFatAmount, cholesterolAmount, SodiumAmount,
            totalCarbonhydrateAmount, dietaryFiberAmount, totalSugarAmount, proteinAmount,
            vitaminAmount, calciumAmount, ironAmount, potassiumAmount;



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


        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        nutritionList = new ArrayList<>();

        //---------fetch nutritions begins-----------------------


        String url = "https://api.edamam.com/api/nutrition-data?app_id=e0d93112&app_key=ae1aa249b69cad6d6b3aff0ca4ea61b1&nutrition-type=cooking&ingr=a%20cup%20of%20coffee";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    //GETTING CALORIE AMOUNT
                    JSONObject totalCalorie = response.getJSONObject("totalNutrients").getJSONObject("ENERC_KCAL");
                    double calorie_nutritionquantity = totalCalorie.getDouble("quantity");

                    calorieAmount.setText(Double.toString(calorie_nutritionquantity));

                    //GETTING TOTAL FAT AMOUNT
                    JSONObject totalFat = response.getJSONObject("totalNutrients").getJSONObject("FAT");
                    double fat_nutritionquantity = totalFat.getDouble("quantity");

                    totalfatAmount.setText(Double.toString(fat_nutritionquantity));

                    //GETTING SATURATED  AMOUNT
                    JSONObject saturatedFat = response.getJSONObject("totalNutrients").getJSONObject("FASAT");
                    double saturatedfat_nutritionquantity = saturatedFat.getDouble("quantity");

                    saturatedFatAmount.setText(Double.toString(saturatedfat_nutritionquantity));

                    //GETTING COLESTEROL AMOUNT
                    JSONObject cholesterol = response.getJSONObject("totalNutrients").getJSONObject("CHOLE");
                    double cholesterol_nutritionquantity = cholesterol.getDouble("quantity");

                    cholesterolAmount.setText(Double.toString(cholesterol_nutritionquantity));

                    //GETTING SODIUM AMOUNT
                    JSONObject sodium = response.getJSONObject("totalNutrients").getJSONObject("NA");
                    double sodium_nutritionquantity = sodium.getDouble("quantity");

                    SodiumAmount.setText(Double.toString(sodium_nutritionquantity));


                    //GETTING TOTAL CARBONHYDRATE AMOUNT
                    JSONObject carbonhydrate = response.getJSONObject("totalNutrients").getJSONObject("CHOCDF");
                    double carbonhydrate_nutritionquantity = carbonhydrate.getDouble("quantity");

                    totalCarbonhydrateAmount.setText(Double.toString(carbonhydrate_nutritionquantity));

                    //GETTING DIETARY FIBER  AMOUNT
                    JSONObject dietaryFiber = response.getJSONObject("totalNutrients").getJSONObject("FIBTG");
                    double dietaryFiber_nutritionquantity = dietaryFiber.getDouble("quantity");

                    dietaryFiberAmount.setText(Double.toString(dietaryFiber_nutritionquantity));

                    //GETTING TOTAL SUGAR AMOUNT
                    JSONObject totalsugar = response.getJSONObject("totalNutrients").getJSONObject("SUGAR");
                    double totalsugar_nutritionquantity = totalsugar.getDouble("quantity");

                    totalSugarAmount.setText(Double.toString(totalsugar_nutritionquantity));

                    //GETTING PROTEIN AMOUNT
                    JSONObject protein = response.getJSONObject("totalNutrients").getJSONObject("PROCNT");
                    double protein_nutritionquantity = protein.getDouble("quantity");

                    proteinAmount.setText(Double.toString(protein_nutritionquantity));


                    //GETTING CALCIUM AMOUNT
                    JSONObject totalCalcium = response.getJSONObject("totalNutrients").getJSONObject("CA");
                    double calcium_nutritionquantity = totalCalcium.getDouble("quantity");

                    calciumAmount.setText(Double.toString(calcium_nutritionquantity));

                    //GETTING IRON AMOUNT
                    JSONObject iron = response.getJSONObject("totalNutrients").getJSONObject("FE");
                    double iron_nutritionquantity = iron.getDouble("quantity");

                    ironAmount.setText(Double.toString(iron_nutritionquantity));

                    //GETTING POTASSIUM AMOUNT
                    JSONObject potassium = response.getJSONObject("totalNutrients").getJSONObject("K");
                    double potassium_nutritionquantity = potassium.getDouble("quantity");

                    potassiumAmount.setText(Double.toString(potassium_nutritionquantity));

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

        RequestQueue requestQueue = Volley.newRequestQueue(this); // 'this' refers to the current context
        requestQueue.add(jsonObjectRequest);
        //---------fetch nutritions ends-----------------------

        searchedFood = SearchValue.getText().toString();



//        calorieAmount.setText(Double.toString(nutritionModel.getCalories()));
//        totalfatAmount.setText(Double.toString(nutritionModel.getTotal_fat()));
//        saturatedFatAmount.setText(Double.toString(nutritionModel.getSaturated_fat()));
//        cholesterolAmount.setText(Double.toString(nutritionModel.getCholesterol()));
//        SodiumAmount.setText(Double.toString(nutritionModel.getSodium()));
//        totalCarbonhydrateAmount.setText(Double.toString(nutritionModel.getTotal_carbonhydrate()));
//        dietaryFiberAmount.setText(Double.toString(nutritionModel.getDietary_fiber()));
//        totalSugarAmount.setText(Double.toString(nutritionModel.getTotal_sugars()));
//        proteinAmount.setText(Double.toString(nutritionModel.getProtein()));
//        calciumAmount.setText(Double.toString(nutritionModel.getCalcium()));
//        ironAmount.setText(Double.toString(nutritionModel.getIron()));
//        potassiumAmount.setText(Double.toString(nutritionModel.getPotassium()));


    }


}