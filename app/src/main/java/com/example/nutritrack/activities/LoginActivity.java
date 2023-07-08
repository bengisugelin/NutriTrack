package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritrack.R;

public class LoginActivity extends AppCompatActivity {


    //Defininf elements

    EditText username;
    EditText password;
    Button loginButton;
    TextView signuppage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.loginActivity_username);
        password = findViewById(R.id.loginActivity_password);
        loginButton = findViewById(R.id.loginActivity_login);
        signuppage = findViewById(R.id.loginActivity_signup);


        //Clickable Text New User - Once Clicked the app goes to Register Page
        signuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTouserRegisterActivity = new Intent(LoginActivity.this,NewUserRegisterActivity.class);
                startActivity(goTouserRegisterActivity);
            }
        });//end of signuppage


        //login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameinfo = username.getText().toString();
                String passwordinfo = password.getText().toString();

                if(usernameinfo.isEmpty() || passwordinfo.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter username/password", Toast.LENGTH_SHORT).show();
                }
                
                if(usernameinfo.equals("admin") && passwordinfo.equals("1234")){
                    Intent goToMainPage = new Intent(LoginActivity.this, CoordinatorActivity.class);
                    startActivity(goToMainPage);
                }else{
                    Toast.makeText(LoginActivity.this, "wrong username/password", Toast.LENGTH_SHORT).show();
                }

                
            }
        });
    }
}