package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPage extends AppCompatActivity {

    Button createSampleData_btn;

    AlertDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        builder = new AlertDialog.Builder(AdminPage.this);
        createSampleData_btn = findViewById(R.id.createSampleData_btn);

        createSampleData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is the code for the Are You sure for the create sample data
                builder.setTitle("Are You Sure?");
                builder.setMessage("This will overwrite the data.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDataBaseHelper myDB = new MyDataBaseHelper(AdminPage.this);

                        myDB.createSampleData();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

    }
}