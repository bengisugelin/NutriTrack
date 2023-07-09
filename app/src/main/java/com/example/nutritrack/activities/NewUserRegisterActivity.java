package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nutritrack.R;

public class NewUserRegisterActivity extends AppCompatActivity {

    Button register;
    EditText fname, lname, username, password, dob, email, height, weight;
    Spinner activitylevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_register);


        register = findViewById(R.id.btnRegister);
        fname = findViewById(R.id.edittext_nameinput);
        lname= findViewById(R.id.edittext_surnameinput);
        username = findViewById(R.id.edittext_username);
        password = findViewById(R.id.edittext_passwordinput);
        dob = findViewById(R.id.edittext_dobinput);
        email = findViewById(R.id.editTextTextEmailAddress);
        height = findViewById(R.id.edittext_height);
        weight= findViewById(R.id.edittext_weight);
        activitylevel=findViewById(R.id.spinner_activitylevel);


        //register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(NewUserRegisterActivity.this, "add new user", Toast.LENGTH_SHORT).show();
            }
        });


    }
}