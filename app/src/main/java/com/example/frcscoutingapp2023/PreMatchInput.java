package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class PreMatchInput extends AppCompatActivity {

    EditText team1Input, team2Input, team3Input, matchNumInput;
    Button pushButton;

    private Context context;
    Activity activity;

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
        });
    }
}