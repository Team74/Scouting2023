package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPage extends AppCompatActivity {

    Button createSampleData_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        createSampleData_btn = findViewById(R.id.createSampleData_btn);

        createSampleData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDataBaseHelper myDB = new MyDataBaseHelper(AdminPage.this);

                myDB.createSampleData();

            }
        });

    }
}