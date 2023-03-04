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
            boolean skipLine = true;
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                Log.d("path123", String.valueOf(colums.length));
                if (colums.length != 23) {
                    Log.d("path123", "Skipping Bad CSV Row");
                    continue;
                }

                if(!skipLine) { //TODO do the parse int for all of them.
                    ContentValues cv = new ContentValues();
                    cv.put(myDB.COLUMN_MATCHNUM, Integer.parseInt(colums[0].trim().substring(1,colums[0].length()-1)));
                    cv.put(myDB.COLUMN_TEAMNUM, Integer.parseInt(colums[1].trim().substring(1,colums[1].length()-1)));

                    cv.put(myDB.COLUMN_autoConesLow, Integer.parseInt(colums[2].trim().substring(1,colums[2].length()-1)));
                    cv.put(myDB.COLUMN_autoConesMid, Integer.parseInt(colums[3].trim().substring(1,colums[3].length()-1)));
                    cv.put(myDB.COLUMN_autoConesHigh, Integer.parseInt(colums[4].trim().substring(1,colums[4].length()-1)));
                    cv.put(myDB.COLUMN_autoConesTotal, Integer.parseInt(colums[5].trim().substring(1,colums[5].length()-1)));
                    cv.put(myDB.COLUMN_autoCubesLow, Integer.parseInt(colums[6].trim().substring(1,colums[6].length()-1)));
                    cv.put(myDB.COLUMN_autoCubesMid, Integer.parseInt(colums[7].trim().substring(1,colums[7].length()-1)));
                    cv.put(myDB.COLUMN_autoCubesHigh, Integer.parseInt(colums[8].trim().substring(1,colums[8].length()-1)));
                    cv.put(myDB.COLUMN_autoCubesTotal, Integer.parseInt(colums[9].trim().substring(1,colums[9].length()-1)));
                    cv.put(myDB.COLUMN_autoBalance, Integer.parseInt(colums[10].trim().substring(1,colums[10].length()-1)));

                    cv.put(myDB.COLUMN_teleOpConesLow, Integer.parseInt(colums[11].trim().substring(1,colums[11].length()-1)));
                    cv.put(myDB.COLUMN_teleOpConesMid, Integer.parseInt(colums[12].trim().substring(1,colums[12].length()-1)));
                    cv.put(myDB.COLUMN_teleOpConesHigh, Integer.parseInt(colums[13].trim().substring(1,colums[13].length()-1)));
                    cv.put(myDB.COLUMN_teleOpConesTotal, Integer.parseInt(colums[14].trim().substring(1,colums[14].length()-1)));
                    cv.put(myDB.COLUMN_teleOpCubesLow, Integer.parseInt(colums[15].trim().substring(1,colums[15].length()-1)));
                    cv.put(myDB.COLUMN_teleOpCubesMid, Integer.parseInt(colums[16].trim().substring(1,colums[16].length()-1)));
                    cv.put(myDB.COLUMN_teleOpCubesHigh, Integer.parseInt(colums[17].trim().substring(1,colums[17].length()-1)));
                    cv.put(myDB.COLUMN_teleOpCubesTotal, Integer.parseInt(colums[18].trim().substring(1,colums[18].length()-1)));
                    cv.put(myDB.COLUMN_teleOpBalance, Integer.parseInt(colums[19].trim().substring(1,colums[19].length()-1)));

                    cv.put(myDB.COLUMN_autonWorked, Integer.parseInt(colums[20].trim().substring(1,colums[20].length()-1)));
                    cv.put(myDB.COLUMN_Broke, Integer.parseInt(colums[21].trim().substring(1,colums[21].length()-1)));
                    cv.put(myDB.COLUMN_Defense, Integer.parseInt(colums[22].trim().substring(1,colums[22].length()-1)));

                    db.insert("Match_Data", null, cv);
                    Toast.makeText(context, "Imported CSV", Toast.LENGTH_SHORT).show();
                    Log.d("path123", "yes");
                }else{
                    skipLine = false;
                }
            }
        } catch (IOException e) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            Log.d("path123", "no");
            e.printStackTrace();
        }
    }

}