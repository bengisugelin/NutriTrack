package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

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
            vitaminAmount, calciumAmount, ironAmount, potassiumAmount, foodtitle;

    ImageButton btn_searchButton;
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


                            for (int i = 0; i<keys.size(); i++){
                                JSONObject obj = response.getJSONObject("totalNutrients").getJSONObject(keys.get(i));
                                double quantity = obj.getDouble("quantity");
                                String unit = obj.getString("unit");

                                String formatAmount = decimalFormat.format(quantity);
                                texviewnames.get(i).setText(formatAmount + " " +unit);

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


    }

}