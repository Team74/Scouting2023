package com.example.frcscoutingapp2023;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AdminPage extends AppCompatActivity {

    Button createSampleData_btn, exportCSV_btn, deleteAll_btn, importCSV_btn, test_btn;

    AlertDialog dialog;
    AlertDialog.Builder builder;
    CSV csv;
    String baseDir;
    Intent myFileIntent;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        csv = new CSV(AdminPage.this);
        baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;
        builder = new AlertDialog.Builder(AdminPage.this);
        createSampleData_btn = findViewById(R.id.createSampleData_btn);
        exportCSV_btn = findViewById(R.id.exportCSV_btn);
        deleteAll_btn = findViewById(R.id.deleteAll_btn);
        importCSV_btn = findViewById(R.id.importCSV_btn);
        test_btn = findViewById(R.id.test_btn);

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

        exportCSV_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This is the code for the Are You sure for the create sample data
                builder.setTitle("Are You Sure?");
                builder.setMessage("This will export the data as a CSV.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        csv.exportMatchData(baseDir);
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

        deleteAll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This is the code for the Are You sure for the create sample data
                builder.setTitle("Are You Sure?");
                builder.setMessage("This will DELETE ALL of the data");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDataBaseHelper myDB = new MyDataBaseHelper(AdminPage.this);
                        SQLiteDatabase db = myDB.getWritableDatabase();
                        myDB.onUpgrade(db, 0, 0);
                        Toast.makeText(AdminPage.this, "All Data Deleted", Toast.LENGTH_SHORT).show();
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

        importCSV_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent, 10);

            }
        });

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filePath != null)
                {
                    importCSV(filePath);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("Path123", String.valueOf(requestCode));
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                     Uri uri = null;
                     if(data != null)
                     {
                         uri = data.getData();
                         filePath = uri.getPath(); //TODO get the true file path, as it crashes rn
                         Log.d("Path123", filePath);
                     }

                }
                break;
            default:
                Log.d("path123", "failed");
                break;
        }
    }

    void importCSV(String mCSVfile)
    {
        Context context = this;
        mCSVfile = "test_data.csv";
        AssetManager manager = context.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(mCSVfile);
            Log.d("path123", "confirm");
        } catch (IOException e) {
            Log.d("path123", "error");
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        MyDataBaseHelper myDB = new MyDataBaseHelper(this);
        SQLiteDatabase db = myDB.getWritableDatabase();
        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                Log.d("path123", String.valueOf(colums.length));
                if (colums.length != 23) {
                    Log.d("path123", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues(3);
                cv.put(myDB.COLUMN_MATCHNUM, colums[0].trim());
                cv.put(myDB.COLUMN_TEAMNUM, colums[1].trim());
                
                cv.put(myDB.COLUMN_autoConesLow, colums[2].trim());
                cv.put(myDB.COLUMN_autoConesMid, colums[3].trim());
                cv.put(myDB.COLUMN_autoConesHigh, colums[4].trim());
                cv.put(myDB.COLUMN_autoConesTotal, colums[5].trim());
                cv.put(myDB.COLUMN_autoCubesLow, colums[6].trim());
                cv.put(myDB.COLUMN_autoCubesMid, colums[7].trim());
                cv.put(myDB.COLUMN_autoCubesHigh, colums[8].trim());
                cv.put(myDB.COLUMN_autoCubesTotal, colums[9].trim());
                cv.put(myDB.COLUMN_autoBalance, colums[10].trim());

                cv.put(myDB.COLUMN_teleOpConesLow, colums[11].trim());
                cv.put(myDB.COLUMN_teleOpConesMid, colums[12].trim());
                cv.put(myDB.COLUMN_teleOpConesHigh, colums[13].trim());
                cv.put(myDB.COLUMN_teleOpConesTotal, colums[14].trim());
                cv.put(myDB.COLUMN_teleOpCubesLow, colums[15].trim());
                cv.put(myDB.COLUMN_teleOpCubesMid, colums[16].trim());
                cv.put(myDB.COLUMN_teleOpCubesHigh, colums[17].trim());
                cv.put(myDB.COLUMN_teleOpCubesTotal, colums[18].trim());
                cv.put(myDB.COLUMN_teleOpBalance, colums[19].trim());

                cv.put(myDB.COLUMN_autonWorked, colums[20].trim());
                cv.put(myDB.COLUMN_Broke, colums[21].trim());
                cv.put(myDB.COLUMN_Defense, colums[22].trim());

                db.insert("Match_Data", null, cv);
                Log.d("path123", "yes");
            }
        } catch (IOException e) {
            Log.d("path123", "no");
            e.printStackTrace();
        }
    }

}