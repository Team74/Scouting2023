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

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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

    void importCSV(Uri csvFileUri)
    {
        Context context = this;

        try {
            MyDataBaseHelper myDB = new MyDataBaseHelper(this);
            SQLiteDatabase db = myDB.getWritableDatabase();

            // open file and attach a file reader to the uri
            FileReader fileReader = new FileReader(
                    this.getContentResolver()
                            .openFileDescriptor(csvFileUri, "r")
                            .getFileDescriptor()
            );

            // now attach a CSV reader to file reader
            CSVReader reader = new CSVReader(fileReader);

            // create a CSV and DB record that we will fill in
            String[] csvLine;

            // for each record returned from the CSV file, add a record to DB
            while ((csvLine = reader.readNext()) != null) {

                // check for the CSV header row and skip it
                if (csvLine[0].equals("match_num")) {
                    continue;
                }

                ContentValues cv = new ContentValues();
                cv.put(myDB.COLUMN_MATCHNUM, Integer.parseInt(csvLine[0]));
                cv.put(myDB.COLUMN_TEAMNUM, Integer.parseInt(csvLine[1]));

                cv.put(myDB.COLUMN_autoConesLow, Integer.parseInt(csvLine[2]));
                cv.put(myDB.COLUMN_autoConesMid, Integer.parseInt(csvLine[3]));
                cv.put(myDB.COLUMN_autoConesHigh, Integer.parseInt(csvLine[4]));
                cv.put(myDB.COLUMN_autoConesTotal, Integer.parseInt(csvLine[5]));
                cv.put(myDB.COLUMN_autoCubesLow, Integer.parseInt(csvLine[6]));
                cv.put(myDB.COLUMN_autoCubesMid, Integer.parseInt(csvLine[7]));
                cv.put(myDB.COLUMN_autoCubesHigh, Integer.parseInt(csvLine[8]));
                cv.put(myDB.COLUMN_autoCubesTotal, Integer.parseInt(csvLine[9]));
                cv.put(myDB.COLUMN_autoBalance, Integer.parseInt(csvLine[10]));

                cv.put(myDB.COLUMN_teleOpConesLow, Integer.parseInt(csvLine[11]));
                cv.put(myDB.COLUMN_teleOpConesMid, Integer.parseInt(csvLine[12]));
                cv.put(myDB.COLUMN_teleOpConesHigh, Integer.parseInt(csvLine[13]));
                cv.put(myDB.COLUMN_teleOpConesTotal, Integer.parseInt(csvLine[14]));
                cv.put(myDB.COLUMN_teleOpCubesLow, Integer.parseInt(csvLine[15]));
                cv.put(myDB.COLUMN_teleOpCubesMid, Integer.parseInt(csvLine[16]));
                cv.put(myDB.COLUMN_teleOpCubesHigh, Integer.parseInt(csvLine[17]));
                cv.put(myDB.COLUMN_teleOpCubesTotal, Integer.parseInt(csvLine[18]));
                cv.put(myDB.COLUMN_teleOpBalance, Integer.parseInt(csvLine[19]));

                cv.put(myDB.COLUMN_autonWorked, Integer.parseInt(csvLine[20]));
                cv.put(myDB.COLUMN_Broke, Integer.parseInt(csvLine[21]));
                cv.put(myDB.COLUMN_Defense, Integer.parseInt(csvLine[22]));

                db.insert("Match_Data", null, cv);
                Toast.makeText(context, "Imported CSV", Toast.LENGTH_SHORT).show();
                Log.d("path123", "yes");

            }
        } catch (IOException e) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            Log.d("path123", "no");
            e.printStackTrace();

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}