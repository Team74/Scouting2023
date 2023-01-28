package com.example.frcscoutingapp2023;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//This is the recycler view page with the list of data
public class MatchView extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDataBaseHelper myDB;
    ArrayList<String> _id, matchNum, teamNum, autonTotal, teleCones, teleCubes, scout_balance;//todo make it not just strings

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
        autonTotal = new ArrayList<>();
        teleCones = new ArrayList<>();
        teleCubes = new ArrayList<>();
        scout_balance = new ArrayList<>();


        storeDataInArray();


        customAdapter = new CustomAdapter(MatchView.this, MatchView.this,
                _id, matchNum, teamNum, autonTotal, teleCones, teleCubes, scout_balance);
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
            cursor.moveToLast();
            do
            {
                //syncs the array strings with the right column, make sure the ids match!
                _id.add(cursor.getString(0));

                autonTotal.add(String.valueOf(cursor.getInt(10) + cursor.getInt(6)));
                teleCones.add(cursor.getString(15));
                teleCubes.add(cursor.getString(19));
                scout_balance.add(cursor.getString(20));
                matchNum.add(cursor.getString(1));
                teamNum.add(cursor.getString(2));
            }
            while(cursor.moveToPrevious()); //TODO make it strings and not ids

        }
    }
}