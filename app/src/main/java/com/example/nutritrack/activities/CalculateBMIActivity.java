package com.example.nutritrack.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutritrack.R;

public class CalculateBMIActivity extends AppCompatActivity {

    EditText heightInput;
    EditText weightInput;
    TextView bmiResult;
    Button calculateBmi;
    ImageView bmi_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        heightInput = findViewById(R.id.calculatebmi_height);
        weightInput = findViewById(R.id.calculatebmi_weight);
        bmiResult = findViewById(R.id.bmiResult);
        calculateBmi = findViewById(R.id.btn_bmicalculate);
        bmi_image =(ImageView) findViewById(R.id.bmi_imageview);


        calculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(heightInput.getText().toString().isEmpty() || weightInput.getText().toString().isEmpty()){
                    bmiResult.setText("Please enter your height and weight");
                }else{
                    Double height = Double.parseDouble(heightInput.getText().toString());
                    Double weight = Double.parseDouble(weightInput.getText().toString());

                    Double calculatebmi = weight/((height/100)*(height/100)) ;

                    bmi_image.setVisibility(view.VISIBLE);
                    bmiResult.setText(calculatebmi.toString());
                }
            }
        });




    }
}