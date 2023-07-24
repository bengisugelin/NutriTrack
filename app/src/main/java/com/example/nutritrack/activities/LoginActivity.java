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
import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.repository.DatabaseHeper;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    //Defining elements
    EditText username;
    EditText password;
    Button loginButton;
    TextView signuppage;

    DatabaseHeper databaseHeper = new DatabaseHeper(LoginActivity.this);
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

                String usernamefromdb= "";
                String passwordfromdb="";

                if(usernameinfo.isEmpty() || passwordinfo.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter username/password", Toast.LENGTH_SHORT).show();
                }else{
                    List<UserModel> user = databaseHeper.getAllData(usernameinfo);

                    //Check if the databaase has the username that user entered to the login input area
                    for (int i = 0; i<user.size(); i++){
                        if(user.get(i).getUsername().equals(usernameinfo)){
                            usernamefromdb = user.get(i).getUsername().trim();
                            passwordfromdb = user.get(i).getPassword().trim();
                            // Toast.makeText(LoginActivity.this, usernamefromdb + " "+ passwordfromdb, Toast.LENGTH_SHORT).show();
                        }
                    }//end of for loop

                    //check if the passwords are matching, if yes, log in, if not, throw a message

                    if (usernamefromdb.equals(usernameinfo.trim()) && passwordfromdb.equals(passwordinfo.trim())) {

                        Intent logintoapp = new Intent(LoginActivity.this, MainActivity.class);
                        //to export the username to the home page
                        Bundle bundle = new Bundle();
                        // bundle.putString("USERNAME", usernameTxt.getText().toString());
                        bundle.putString("USERNAME", usernamefromdb);
                        logintoapp.putExtras(bundle);

                        startActivity(logintoapp);
                    }else{
                        Toast.makeText(LoginActivity.this, "wrong username/password", Toast.LENGTH_SHORT).show();
                    }
                }//end of if else
                }








        });
    }
}