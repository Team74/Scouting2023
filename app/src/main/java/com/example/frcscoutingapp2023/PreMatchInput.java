package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PreMatchInput extends AppCompatActivity {

    EditText team1Input, team2Input, team3Input, matchNumInput;
    Button pushButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_match_input);

        team1Input = findViewById(R.id.team1_input_et);
        team2Input = findViewById(R.id.team2_input_et);
        team3Input = findViewById(R.id.team3_input_et);
        matchNumInput = findViewById(R.id.matchNum_input_et);
    }
}