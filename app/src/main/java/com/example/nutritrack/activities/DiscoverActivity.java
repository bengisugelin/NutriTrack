package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nutritrack.R;
import com.example.nutritrack.adapters.DiscoverRecyclerViewAdapter;
import com.example.nutritrack.models.RecipeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DiscoverActivity extends AppCompatActivity {

    EditText searchedvalue;
    ImageButton searchButton;
    ArrayList<RecipeModel> recipeModelList = new ArrayList<>();

    String searchedfood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_discover);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME", "user");


        searchedvalue = findViewById(R.id.discover_editText_searchedfood);


        searchButton = findViewById(R.id.recipe_searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchedfood = searchedvalue.getText().toString();
                String foodnametoinserttoURL = searchedfood.replace(" ", "%20").toLowerCase();

              //  Toast.makeText(DiscoverActivity.this, foodnametoinserttoURL, Toast.LENGTH_SHORT).show();
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://recipe-by-api-ninjas.p.rapidapi.com/v1/recipe?query="+foodnametoinserttoURL)
                        .get()
                        .addHeader("X-RapidAPI-Key", "0bcdcbf499msha7624a7863f76a1p1af981jsnfa988a719a48")
                        .addHeader("X-RapidAPI-Host", "recipe-by-api-ninjas.p.rapidapi.com")
                        .build();



                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        if(response.isSuccessful()){


                            try {
                                String responsebody = response.body().string();
                                JSONArray jsonArray = new JSONArray(responsebody); // Convert the string to a JSON object


                                List<RecipeModel> tempRecipeList = new ArrayList<>();

                                for (int i =0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                                    tempRecipeList.add(new RecipeModel(jsonObject.getString("title"),
                                            jsonObject.getString("servings"),
                                            jsonObject.getString("ingredients"),
                                            jsonObject.getString("instructions")));
                                }


                                DiscoverActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        recipeModelList.clear();
                                        recipeModelList.addAll(tempRecipeList);

                                        RecyclerView recyclerView = findViewById(R.id.discover_recyclerview);
                                        DiscoverRecyclerViewAdapter adapter = new DiscoverRecyclerViewAdapter(DiscoverActivity.this,recipeModelList);
                                        recyclerView.setAdapter(adapter);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(DiscoverActivity.this));


                                    }
                                });


                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }


                        }
                    }
                });
            }
        });




        //BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.discover);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.discover:
                        return true;
                    case R.id.profile:
                        Intent goToProfilePage = new Intent(DiscoverActivity.this, ProfileActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("USERNAME", username);
                        goToProfilePage.putExtras(bundle);
                        startActivity(goToProfilePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.home:
                        Intent goToHomePage = new Intent(DiscoverActivity.this, MainActivity.class);
                        Bundle bundle_home = new Bundle();
                        bundle_home.putString("USERNAME", username);
                        goToHomePage.putExtras(bundle_home);
                        startActivity(goToHomePage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.log:
                        Intent goToLogPage = new Intent(DiscoverActivity.this, LogActivity.class);
                        Bundle bundle_log = new Bundle();
                        bundle_log.putString("USERNAME", username);
                        goToLogPage.putExtras(bundle_log);
                        startActivity(goToLogPage);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;
            }
        });
    }
}