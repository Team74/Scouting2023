package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TeamStatsReview extends AppCompatActivity {

    EditText teamInput;
    Button button;
    TextView avgCones_txt;
    MyDataBaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats_review);

        teamInput = findViewById(R.id.teamNumInput);
        avgCones_txt = findViewById(R.id.avgCones);
        button = findViewById(R.id.teamSummit_btn);

        myDB = new MyDataBaseHelper(TeamStatsReview.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int teamInputInt = Integer.parseInt(teamInput.getText().toString());
                Float avgCones = myDB.getTeamData(teamInputInt, myDB.COLUMN_teleOpConesTotal);
                avgCones_txt.setText(avgCones.toString());
            }
        });
    }
}