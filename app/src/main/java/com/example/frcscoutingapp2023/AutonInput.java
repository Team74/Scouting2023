package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AutonInput extends AppCompatActivity {

    TextView team1Num_tv, team2Num_tv, team3Num_tv, coneTotal;
    String team1Name, team2Name, team3Name ;
    Button minus1Btn, plus1Btn;

    int cones = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton_input);

        team1Num_tv = findViewById(R.id.team1Num_txt);
        team2Num_tv = findViewById(R.id.team2Num_txt);
        team3Num_tv = findViewById(R.id.team3Num_txt);

        minus1Btn = findViewById(R.id.minus_Button1_btn);
        plus1Btn = findViewById(R.id.plus_button1_btn);
        coneTotal = findViewById(R.id.cone_total_tv);



        minus1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cones = cones - 1;
                coneTotal.setText(String.valueOf(cones));
            }
        });

        plus1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cones = cones + 1;
                coneTotal.setText(String.valueOf(cones));
            }
        });

        getAndSetIntentData();

    }


    void getAndSetIntentData()//TODO make sure it works with partial data
    {
        if(getIntent().hasExtra("MatchNum")) //TODO clean up
        {
            //Setting intent from data
            team1Name = String.valueOf(getIntent().getIntExtra("Team1Num", 0));
            team1Num_tv.setText(team1Name);

            team2Name = String.valueOf(getIntent().getIntExtra("Team2Num", 0));
            team2Num_tv.setText(team2Name);

            team3Name = String.valueOf(getIntent().getIntExtra("Team3Num", 0));
            team3Num_tv.setText(team3Name);

        }else{
            Toast.makeText(this, "No or Partial Data", Toast.LENGTH_SHORT).show();
        }
    }


}