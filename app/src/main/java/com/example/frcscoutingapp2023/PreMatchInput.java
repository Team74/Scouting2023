package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class PreMatchInput extends AppCompatActivity {

    EditText team1Input, team2Input, team3Input, matchNumInput;
    Button pushButton;

    private Context context;
    Activity activity;
    String[] teamList = {"51", "70", "74", "107", "226", "469", "494", "818", "910", "1481", "1684",
    "1701", "1940", "2619", "3175", "3322", "3618", "3655", "3688", "3770", "4003", "4130",
    "4237", "4422", "4983", "5152", "5216", "5235", "5314", "5505", "5712", "5860", "7197",
    "7226", "8426", "8728", "9182", "9207", "9242", "9255"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_match_input);

        team1Input = findViewById(R.id.team1_input_et);
        team2Input = findViewById(R.id.team2_input_et);
        team3Input = findViewById(R.id.team3_input_et);
        matchNumInput = findViewById(R.id.matchNum_input_et);
        pushButton = findViewById(R.id.toAutonButton);

        activity = PreMatchInput.this;

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //TODO make the input and string more different
                //Checks if the edit texts are empty, as if they are, it crashes
                if((TextUtils.isEmpty(team1Input.getText().toString())) || (TextUtils.isEmpty(team2Input.getText().toString()))
                        || (TextUtils.isEmpty(team3Input.getText().toString())) || (TextUtils.isEmpty(matchNumInput.getText().toString())))
                {
                   FailedToast("-1");
                }else
                {
                    if(!Arrays.asList(teamList).contains(team1Input.getText().toString()))
                    {
                        FailedToast("1");
                        return;
                    }
                    if(!Arrays.asList(teamList).contains(team2Input.getText().toString()))
                    {
                        FailedToast("2");
                        return;
                    }
                    if(!Arrays.asList(teamList).contains(team3Input.getText().toString()))
                    {
                        FailedToast("3");
                        return;
                    }
                    int team1Num = Integer.parseInt(team1Input.getText().toString());
                    int team2Num = Integer.parseInt(team2Input.getText().toString());
                    int team3Num = Integer.parseInt(team3Input.getText().toString());
                    int matchNum = Integer.parseInt(matchNumInput.getText().toString());

                    Intent intent = new Intent(PreMatchInput.this, AutonInput.class);
                    intent.putExtra("Team1Num", team1Num);
                    intent.putExtra("Team2Num", team2Num);
                    intent.putExtra("Team3Num", team3Num);
                    intent.putExtra("MatchNum", matchNum);
                    activity.startActivityForResult(intent, 1);
                }
            }
        });
    }

    void FailedToast(String teamNum)
    {
        if(teamNum == "-1")
        {
            Toast.makeText(this, "Please enter data into all boxes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Wrong Team Number in Box " + teamNum, Toast.LENGTH_SHORT).show();
        }
    }
}