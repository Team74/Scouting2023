package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostMatchInput extends AppCompatActivity {

    Button pushBtn;
    Activity activity = PostMatchInput.this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_match_input);
        
        pushBtn = findViewById(R.id.push_btn);
        
        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int team1Num = getIntent().getIntExtra("Team1Num", 0);
                int team2Num = getIntent().getIntExtra("Team2Num", 0);
                int team3Num = getIntent().getIntExtra("Team3Num", 0);
                int matchNum = getIntent().getIntExtra("MatchNum", 0);

                int autoHighCones = getIntent().getIntExtra("autoHighCones", 0);
                int autoMidCones = getIntent().getIntExtra("autoMidCones", 0);
                int autoLowCones = getIntent().getIntExtra("autoLowCones", 0);
                int autoHighCubes = getIntent().getIntExtra("autoHighCubes", 0);
                int autoMidCubes = getIntent().getIntExtra("autoMidCubes", 0);
                int autoLowCubes = getIntent().getIntExtra("autoLowCubes", 0);
                int autoBalance = getIntent().getIntExtra("autoBalance", 0);

                int teleOpHighCones = getIntent().getIntExtra("teleOpHighCones", 0);
                int teleOpMidCones = getIntent().getIntExtra("teleOpMidCones", 0);
                int teleOpLowCones = getIntent().getIntExtra("teleOpLowCones", 0);
                int teleOpHighCubes = getIntent().getIntExtra("teleOpHighCubes", 0);
                int teleOpMidCubes = getIntent().getIntExtra("teleOpMidCubes", 0);
                int teleOpLowCubes = getIntent().getIntExtra("teleOpLowCubes", 0);
                int teleOpBalance = getIntent().getIntExtra("teleOpBalance", 0);

                MyDataBaseHelper myDB = new MyDataBaseHelper(PostMatchInput.this);
                myDB.addMatch(matchNum, team1Num, autoHighCones, autoMidCones, autoLowCones, autoHighCubes, autoMidCubes, autoLowCubes, teleOpHighCones,
                        teleOpMidCones, teleOpLowCones, teleOpHighCubes, teleOpMidCubes, teleOpLowCubes, autoBalance, teleOpBalance);

                Intent intent = new Intent(PostMatchInput.this, MatchView.class);
                activity.startActivityForResult(intent, 1);
            }
        });
    }
}