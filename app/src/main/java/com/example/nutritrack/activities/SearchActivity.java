package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.nutritrack.R;

public class SearchActivity extends AppCompatActivity {

    TextView passedNameInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);

        String name = getIntent().getStringExtra("NAME");
        passedNameInfo = findViewById(R.id.recycler_selected_name);

        passedNameInfo.setText(name);
    }
}