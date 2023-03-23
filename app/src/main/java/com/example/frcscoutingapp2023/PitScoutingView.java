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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//This is the recycler view page with the list of data
public class PitScoutingView extends AppCompatActivity {

    RecyclerView recyclerView;
    ExtendedFloatingActionButton add_button;

    MyDataBaseHelper myDB;
    ArrayList<String> _id, teamNum, teamName;//todo make it not just strings

    PitScouting_CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_view);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.addTeam_btn);
        myDB = new MyDataBaseHelper(PitScoutingView.this);
        _id = new ArrayList<>();
        teamNum = new ArrayList<>();
        teamName = new ArrayList<>();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PitScoutingView.this, pit_scouting_input.class);
                startActivity(intent);
            }
        });


        storeDataInArray();
        //loadImageFromStorage("/data/data/com.example.frcscoutingapp2023/app_imageDir");

        customAdapter = new PitScouting_CustomAdapter(PitScoutingView.this, PitScoutingView.this,
                _id, teamNum, teamName);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PitScoutingView.this));

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
        Cursor cursor = myDB.readAllPitScoutingData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            cursor.moveToLast();
            do
            {
                //syncs the array strings with the right column, make sure the ids match!
                _id.add(cursor.getString(0));
                teamNum.add(cursor.getString(1));
                teamName.add(cursor.getString(2));
            }
            while(cursor.moveToPrevious()); //TODO make it strings and not ids

        }
    }

    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.imageView);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}