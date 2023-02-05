package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataReviewMain extends AppCompatActivity {

    Button teamReview_btn, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_review_main);

        teamReview_btn = findViewById(R.id.teamReview_btn);
        button = findViewById(R.id.teamStats_btn);

        teamReview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataReviewMain.this, MatchDataTable.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataReviewMain.this, TeamStatsReview.class);
                startActivity(intent);
            }
        });

    }
}