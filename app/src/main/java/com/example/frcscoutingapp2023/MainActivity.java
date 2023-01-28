package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//This is the main menu with the buttons to go to different screens
public class MainActivity extends AppCompatActivity {
    private Button button, adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.matchInputButton);
        adminButton = findViewById(R.id.toAdmin_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatchView.class);
                startActivity(intent);
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });
    }
}