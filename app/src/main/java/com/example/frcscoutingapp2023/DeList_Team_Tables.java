package com.example.frcscoutingapp2023;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

//This is the recycler view page with the list of data
public class DeList_Team_Tables extends AppCompatActivity {

    RecyclerView recyclerView;
    ExtendedFloatingActionButton add_button;

    MyDataBaseHelper myDB;
    ArrayList<String> teamNum;//todo make it not just strings

    DeList_CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_list_team_tables);

        recyclerView = findViewById(R.id.delist_recyclerView);
        myDB = new MyDataBaseHelper(DeList_Team_Tables.this);
        teamNum = new ArrayList<>();
        Button back_btn = findViewById(R.id.back_btn);


        storeDataInArray();

        customAdapter = new DeList_CustomAdapter(DeList_Team_Tables.this, DeList_Team_Tables.this, teamNum);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DeList_Team_Tables.this));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeList_Team_Tables.this, MatchDataTable.class);
                intent.putExtra("delistTeams", customAdapter.getDelistedTeams());
                Log.d("path123", String.valueOf(customAdapter.getDelistedTeams().length));
                startActivity(intent);
            }
        });
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
        Cursor cursor = myDB.getAllTeamNumbers();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            cursor.moveToLast();
            do
            {
                //syncs the array strings with the right column, make sure the ids match!
                teamNum.add(cursor.getString(2));
            }
            while(cursor.moveToPrevious()); //TODO make it strings and not ids

        }
    }

}