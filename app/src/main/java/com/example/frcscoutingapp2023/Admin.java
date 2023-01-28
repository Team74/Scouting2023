package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class Admin extends AppCompatActivity {

    EditText passwordInput;
    Button check;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        passwordInput = findViewById(R.id.passwordInput_et);
        check = findViewById(R.id.checkPassword_btn);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordInput.getText().toString().equals("chaos74"))
                {
                    Toast.makeText(context, "You may Enter", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Not Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}