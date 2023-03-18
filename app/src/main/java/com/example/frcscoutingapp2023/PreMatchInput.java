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
    String[] teamList = {"27", "70", "74", "85", "107", "141", "494", "1025", "1918", "2054", "2959", "3234", "3357", "3458", "4003", "4337", "4395", "4855", "4967", "5150",
    "5248", "5462", "5470", "5567", "5610", "5702", "5927", "5980", "6090", "6094", "6114", "6128", "6428", "6877", "7248", "8612", "9206", "9222", "9248", "9266"};

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