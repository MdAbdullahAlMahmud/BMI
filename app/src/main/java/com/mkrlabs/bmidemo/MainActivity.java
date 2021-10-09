package com.mkrlabs.bmidemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mkrlabs.customstatusbar.CustomStatusBar;

public class MainActivity extends AppCompatActivity {

    TextView StatusTV, bmiScoreTV;
    EditText bmiWeightEDT, bmiFeetEDT, bmiInchEDT;
    AppCompatButton bmiCalculateBTN;
    ConstraintLayout constraintLayout;
    String bmi_status;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomStatusBar.transparentStatusBarWithIcon(this,false);
        setContentView(R.layout.activity_main);

        StatusTV = findViewById(R.id.bmiStatusTV);
        bmiScoreTV = findViewById(R.id.bmiScoreTV);
        bmiWeightEDT = findViewById(R.id.bmiWeightEDT);
        bmiFeetEDT = findViewById(R.id.bmiFeetEDT);
        bmiInchEDT = findViewById(R.id.bmiInchEDT);
        bmiCalculateBTN = findViewById(R.id.bmiCalculateBTN);
        constraintLayout = findViewById(R.id.constraintLayout);


        bmiCalculateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = bmiWeightEDT.getText().toString();
                String feet = bmiFeetEDT.getText().toString();
                String inch = bmiInchEDT.getText().toString();

                if (weight.isEmpty() || feet.isEmpty() || inch.isEmpty()){
                    Toast.makeText(MainActivity.this, "Field Required", Toast.LENGTH_SHORT).show();
                }else {
                    int feetInInt = Integer.parseInt(feet);
                    int inchInInt = Integer.parseInt(inch);

                    int weightInInt = Integer.parseInt(weight);

                    double heightInMeter = ((feetInInt * 12) + inchInInt)*(0.0254);

                    Log.v("Tag","Weight "+ weightInInt + " height" + heightInMeter);
                    calculateBmi(weightInInt, heightInMeter);
                }

            }
        });

    }

    private void calculateBmi(int weightInInt, double heightInMeter) {

        // BMI = kg / (m*m)
        // 1 feet = 12 inch
        //1 inch = .0254m

        double BMI = (weightInInt / (heightInMeter * heightInMeter));

        Log.v("Tag","BMI "+BMI);

        if (BMI < 18.5) {
            bmi_status = "Under Weight";
            color = getResources().getColor(R.color.under_weight_color);
        } else if (BMI >= 18.5 && BMI<= 24.9) {
            bmi_status = "Healthy";
            color = getResources().getColor(R.color.healthy_color);
        } else if (BMI >= 25.0 && BMI<=29.9) {
            bmi_status = "Healthy";
            color = getResources().getColor(R.color.healthy_color);
        }else {
            bmi_status = "Obese";
            color = getResources().getColor(R.color.obese_color);
        }


        StatusTV.setText(bmi_status);
        String formattedBMI = String.format("%.02f", BMI);
        bmiScoreTV.setText("BMI : "+formattedBMI);
        constraintLayout.setBackgroundColor(color);

    }


}