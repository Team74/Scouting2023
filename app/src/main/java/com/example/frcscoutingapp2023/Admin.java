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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Admin extends AppCompatActivity {

    EditText passwordInput;
    Button check;
    Context context = this;
    ImageView secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        passwordInput = findViewById(R.id.passwordInput_et);
        check = findViewById(R.id.checkPassword_btn);
        secret = findViewById(R.id.gullitine);
        secret.setAlpha((float) 0);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordInput.getText().toString().equals("ch33z")) {
                    Intent intent = new Intent(Admin.this, AdminPage.class);
                    startActivity(intent);
                }
                else if(passwordInput.getText().toString().equals("guillotine"))
                {
                    secret.setAlpha((float) 1);
                }else{
                    Toast.makeText(context, "Not Correct", Toast.LENGTH_SHORT).show();
                    passwordInput.getText().clear();
                }
            }
        });

    }
}