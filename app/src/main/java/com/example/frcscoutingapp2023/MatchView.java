package com.example.frcscoutingapp2023;

import static com.example.frcscoutingapp2023.MyDataBaseHelper.COLUMN_teleOpConesTotal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//This is the recycler view page with the list of data
public class MatchView extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDataBaseHelper myDB;
    ArrayList<String> _id, matchNum, teamNum, scout_cones, scout_cubes, scout_balance;//todo make it not just strings

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_view);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.addMatchButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatchView.this, PreMatchInput.class);
                startActivity(intent);
            }
        });


        myDB = new MyDataBaseHelper(MatchView.this);
        _id = new ArrayList<>();
        matchNum = new ArrayList<>();
        teamNum = new ArrayList<>();
        scout_cones = new ArrayList<>();
        scout_cubes = new ArrayList<>();
        scout_balance = new ArrayList<>();


        storeDataInArray();


        customAdapter = new CustomAdapter(MatchView.this, MatchView.this,
                _id, matchNum, teamNum, scout_cones, scout_cubes, scout_balance);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MatchView.this));

    }

    //refresh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1)
        {
            recreate();
        }
    }

    @SuppressLint("Range")
    void storeDataInArray()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext())
            {
                //syncs the array strings with the right column, make sure the ids match!
                _id.add(cursor.getString(0));

                scout_cones.add(cursor.getString(15));
                scout_cubes.add(cursor.getString(19));
                scout_balance.add(cursor.getString(20));
                matchNum.add(cursor.getString(1));
                teamNum.add(cursor.getString(2));


            }
        }
    }
}