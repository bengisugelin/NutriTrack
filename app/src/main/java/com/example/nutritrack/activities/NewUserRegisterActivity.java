package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nutritrack.R;
import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.models.nutritionModel;
import com.example.nutritrack.repository.DatabaseHeper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewUserRegisterActivity extends AppCompatActivity {
    Button register;
    EditText fname, lname, username, password, dob, email, height, weight;
    Spinner activitylevel;
    DatePickerDialog datePickerDialog;

    RadioGroup radioGroup;
    RadioButton radioButtonmale, radiobuttonfemale;
    String sex;

    //database helper
    DatabaseHeper NutriTrackdb = new DatabaseHeper(NewUserRegisterActivity.this);
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
        radioGroup = findViewById(R.id.radioGroup);



        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });



        //register button
        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(NewUserRegisterActivity.this, "Please select your biological sex", Toast.LENGTH_SHORT).show();
                }else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMale){
                    sex = "male";
                }else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFemale){
                    sex = "female";
                }


                UserModel userModel = new UserModel(fname.getText().toString().trim(),
                        lname.getText().toString().trim(),
                        username.getText().toString().trim(),
                        password.getText().toString().trim(),
                        sex,
                        dob.getText().toString().trim(),
                        email.getText().toString().trim(),
                        Double.parseDouble(height.getText().toString().trim()),
                        Double.parseDouble(weight.getText().toString().trim()),
                        activitylevel.getSelectedItem().toString());


//                boolean success = NutriTrackdb.addUser(userModel);
                NutriTrackdb.addUser(userModel);


                //nutrotionmodel
                nutritionModel nutritionModel = new nutritionModel("none",0,0,0,0,0,0,0,0,0,0,0,0);
                NutriTrackdb.addNutrition(nutritionModel);

//                Toast.makeText(NewUserRegisterActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                Intent goToLoginpage = new Intent(NewUserRegisterActivity.this, LoginActivity.class);
                startActivity(goToLoginpage);
            }
        });

    }

    private void openDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(NewUserRegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                int month = monthOfYear + 1;
                String strMonth = ""+ month;
                String strDayOfMonth= "" + dayOfMonth;
                if (month<10){
                    strMonth="0"+strMonth;
                }
                if (dayOfMonth<10){
                    strDayOfMonth="0"+strDayOfMonth;
                }
                String date= year + "-" + strMonth + "-" + strDayOfMonth;
                dob.setText(date );
            }
        },year, month,day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();

    }//end of opendatepicker


}