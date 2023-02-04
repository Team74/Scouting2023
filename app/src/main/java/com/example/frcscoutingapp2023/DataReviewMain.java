package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataReviewMain extends AppCompatActivity {

    Button teamReview_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_review_main);

        teamReview_btn = findViewById(R.id.teamReview_btn);

        teamReview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataReviewMain.this, TeamReview.class);
                startActivity(intent);
            }
        });

    }
}