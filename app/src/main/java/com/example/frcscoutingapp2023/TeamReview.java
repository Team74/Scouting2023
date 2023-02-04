package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class TeamReview extends AppCompatActivity {

    MyDataBaseHelper myDB;
   int teamNumInput = 324;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_review);

        myDB = new MyDataBaseHelper(TeamReview.this);

        Cursor cursor = myDB.getTeamData(teamNumInput);
        Log.d("table test", String.valueOf(cursor.getCount()));
       // Log.d("table test", String.valueOf(cursor.getString(19)));
    }
}