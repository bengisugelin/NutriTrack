package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nutritrack.R;
import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.repository.DatabaseHeper;

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
        fname = findViewById(R.id.edittext_username);
        lname= findViewById(R.id.edittext_passwordinput);
        username = findViewById(R.id.edittext_nameinput);
        password = findViewById(R.id.edittext_surnameinput);
        dob = findViewById(R.id.edittext_dobinput);
        email = findViewById(R.id.editTextTextEmailAddress);
        height = findViewById(R.id.edittext_height);
        weight= findViewById(R.id.edittext_weight);
        activitylevel=findViewById(R.id.spinner_activitylevel);


        //register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel userModel = new UserModel(fname.getText().toString().trim(),
                        lname.getText().toString().trim(),
                        username.getText().toString().trim(),
                        password.getText().toString().trim(),
                        dob.getText().toString().trim(),
                        email.getText().toString().trim(),
                        Double.parseDouble(height.getText().toString().trim()),
                        Double.parseDouble(weight.getText().toString().trim()),
                        "active");


                //database helper

                DatabaseHeper NutriTrackdb = new DatabaseHeper(NewUserRegisterActivity.this);
                boolean success = NutriTrackdb.addUser(userModel);


                Toast.makeText(NewUserRegisterActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                Intent goToLoginpage = new Intent(NewUserRegisterActivity.this, LoginActivity.class);
                startActivity(goToLoginpage);
            }
        });


    }
}