package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class pit_scouting_input extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button takePic_btn, push_btn;
    EditText teamNum_txt, dimentions_txt, autonPieces_txt, teamName_txt;
    CheckBox mobility_cb, autoBal_cb, low_cb, mid_cb, high_cb, climb_cb, floor_cb, substation_cb,
            cones_cb, cubes_cb;
    Spinner lang_spin;
    int langPosInt = 0;
    String langType;
    MyDataBaseHelper myDB = new MyDataBaseHelper(this);
    File mypath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_input);

        push_btn = findViewById(R.id.pitPush_btn);
        teamNum_txt = findViewById(R.id.teamNumber_txt);
        dimentions_txt = findViewById(R.id.Dimentions_txt);
        autonPieces_txt = findViewById(R.id.autonPieces_txt);
        teamName_txt = findViewById(R.id.teamName_txt);

        mobility_cb = findViewById(R.id.mobility_cb);
        autoBal_cb = findViewById(R.id.autoBalance_cb);
        low_cb = findViewById(R.id.low_cb);
        mid_cb = findViewById(R.id.mid_cb);
        high_cb = findViewById(R.id.high_cb);
        climb_cb = findViewById(R.id.climb_cb);
        floor_cb = findViewById(R.id.floor_cb);
        substation_cb = findViewById(R.id.substation_cb);
        cones_cb = findViewById(R.id.cones_cb);
        cubes_cb = findViewById(R.id.cubes_cb);

        lang_spin = findViewById(R.id.lang_spin);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang_spin.setAdapter(adapter);

        lang_spin.setSelection(langPosInt);
        lang_spin.setOnItemSelectedListener(this);



        takePic_btn = findViewById(R.id.takePicture_btn);
        takePic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent, 1337);
            }
        });

        push_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();

                if(TextUtils.isEmpty(teamName_txt.getText().toString()) || TextUtils.isEmpty(dimentions_txt.getText().toString()) ||
                        TextUtils.isEmpty(autonPieces_txt.getText().toString()) || TextUtils.isEmpty(teamNum_txt.getText().toString()))
                {
                    Toast.makeText(pit_scouting_input.this, "Enter Data In All Boxes", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("path123", teamName_txt.getText().toString());
                try {
                    cv.put(myDB.COLUMN_PIT_TEAMNUM, Integer.parseInt(teamNum_txt.getText().toString()));
                    cv.put(myDB.COLUMN_PIT_NAME, teamName_txt.getText().toString());
                    cv.put(myDB.COLUMN_DIMENSIONS, Integer.parseInt(dimentions_txt.getText().toString()));
                    cv.put(myDB.COLUMN_AUTON_PIECES, Integer.parseInt(autonPieces_txt.getText().toString()));
                    cv.put(myDB.COLUMN_AUTON_LINE, convertBoolToInt(mobility_cb));
                    cv.put(myDB.COLUMN_AUTON_CENTER, convertBoolToInt(autoBal_cb));
                    cv.put(myDB.COLUMN_TELEOP_LOW, convertBoolToInt(low_cb));
                    cv.put(myDB.COLUMN_TELEOP_MID, convertBoolToInt(mid_cb));
                    cv.put(myDB.COLUMN_TELEOP_HIGH, convertBoolToInt(high_cb));
                    cv.put(myDB.COLUMN_TELEOP_CLIMB, convertBoolToInt(climb_cb));
                    cv.put(myDB.COLUMN_TELEOP_FLOOR, convertBoolToInt(floor_cb));
                    cv.put(myDB.COLUMN_TELEOP_SUBSTATION, convertBoolToInt(substation_cb));
                    cv.put(myDB.COLUMN_TELEOP_CUBES, convertBoolToInt(cubes_cb));
                    cv.put(myDB.COLUMN_TELEOP_CONES, convertBoolToInt(cones_cb));
                    cv.put(myDB.COLUMN_IMAGE_FILEPATH, "null");
                    cv.put(myDB.COLUMN_PIT_LANGUAGE, langType);

                    myDB.addPitScoutingTeam(0, cv, true);

                    Intent intent = new Intent(pit_scouting_input.this, PitScoutingView.class);
                    startActivity(intent);
                }catch (Exception e)
                {
                    throw e;
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1337) {
            if(data.hasExtra("data"))
            {
               Bitmap image = (Bitmap) data.getExtras().get("data");
                ImageView imageview = (ImageView) findViewById(R.id.robotImageView); //sets imageview as the bitmap
                saveToInternalStorage(image);
                imageview.setImageBitmap(image);

            }
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        mypath = new File(directory,teamNum_txt.getText().toString() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        langType = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    int convertBoolToInt(CheckBox cb)
    {
        if(cb != null)
        {
            if(cb.isChecked())
            {
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }
}