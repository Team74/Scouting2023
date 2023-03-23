package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PitScoutingTeamReview extends AppCompatActivity {

    int pit_id;
    MyDataBaseHelper myDB = new MyDataBaseHelper(this);
    ArrayList teamNum, teamName, dimensions, auton_pieces, auton_line, auton_center,
            teleop_low, teleop_mid, teleop_high, teleop_climb, teleop_floor,
            teleop_substation, teleop_cubes, teleop_cones, image_filepath, language;

    int teamNum_int, dimensions_int, auton_pieces_int, auton_line_int, auton_center_int,
            teleop_low_int, teleop_mid_int, teleop_high_int, teleop_climb_int, teleop_floor_int,
            teleop_substation_int, teleop_cubes_int, teleop_cones_int, language_int;

    String teamName_string;
    String image_filepath_string, language_string;

    TextView teamName_txt, teamNum_txt, shortSide_txt, pieces_txt, mobility_txt, center_txt, lang_txt, placement_txt, climb_txt, pickup_txt;
    ImageView robotImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_team_review);

        teamNum = new ArrayList<>();
        teamName = new ArrayList<>();
        dimensions = new ArrayList<>();
        auton_pieces = new ArrayList<>();
        auton_line = new ArrayList<>();
        auton_center = new ArrayList<>();
        teleop_low = new ArrayList<>();
        teleop_mid = new ArrayList<>();
        teleop_high = new ArrayList<>();
        teleop_climb = new ArrayList<>();
        teleop_floor = new ArrayList<>();
        teleop_substation = new ArrayList<>();
        teleop_cubes = new ArrayList<>();
        teleop_cones = new ArrayList<>();
        image_filepath = new ArrayList<>();
        language = new ArrayList<>();

        getIntentData();

        teamName_txt = findViewById(R.id.teamName_view_txt);
        teamNum_txt = findViewById(R.id.teamNum_view_txt);
        shortSide_txt = findViewById(R.id.shortSide_view_txt);
        pieces_txt = findViewById(R.id.autonPieces_view_txt);
        mobility_txt = findViewById(R.id.mobiltiy_view_txt);
        center_txt = findViewById(R.id.center_view_txt);
        lang_txt = findViewById(R.id.lang_view_txt);
        placement_txt = findViewById(R.id.placement_view_txt);
        climb_txt = findViewById(R.id.climb_view_txt);
        pickup_txt = findViewById(R.id.pickup_view_txt);

        teamName_txt.setText(teamName_string);
        teamNum_txt.setText(String.valueOf(teamNum_int));
        shortSide_txt.setText(String.valueOf(dimensions_int));

        String autoPieces_Text = "Can Get " + String.valueOf(auton_pieces_int) + " In Auto";
        pieces_txt.setText(autoPieces_Text);

        String line_Text = "Error";
        if(auton_line_int == 1)
        {
            line_Text = "Can Cross the Line";
        } else if (auton_line_int == 0)
        {
            line_Text = "Can't Cross the Line";
        }
        mobility_txt.setText(line_Text);

        String center_Text = "Error";
        if(auton_center_int == 1)
        {
            center_Text = "Has a Center Auton";
        } else if (auton_center_int == 0)
        {
            center_Text = "No Center Auton";
        }
        center_txt.setText(center_Text);

        lang_txt.setText(language_string);

        String placement_Text = "";
        if(teleop_low_int == 1)
            placement_Text = placement_Text + " Low ";
        if(teleop_mid_int == 1)
            placement_Text = placement_Text + " Mid ";
        if(teleop_high_int == 1)
            placement_Text = placement_Text + " High ";

        placement_txt.setText("Can Place " + placement_Text);

        String climb_Text = "Error";
        if(teleop_climb_int == 1)
        {
            climb_Text = "Can Climb";
        } else if (teleop_climb_int == 0)
        {
            climb_Text = "Can't Climb";
        }
        climb_txt.setText(climb_Text);

        String pickup_Text = "";
        if(teleop_floor_int == 1)
            pickup_Text = pickup_Text + " Floor ";
        if(teleop_substation_int == 1)
            pickup_Text = pickup_Text + " SubStation ";

        pickup_txt.setText("Can Pick Up From " + pickup_Text);

        loadImageFromStorage("/data/data/com.example.frcscoutingapp2023/app_imageDir");

    }

    void getIntentData()
    {
        if(getIntent().hasExtra("pit_id"))
        {
            pit_id = getIntent().getIntExtra("pit_id", 0);
        }else{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            return;
        }


        Cursor cursor = myDB.readAllPitScoutingData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            cursor.moveToFirst();
            do
            {
                //syncs the array strings with the right column, make sure the ids match!
                teamNum.add(cursor.getString(1));
                teamName.add(cursor.getString(2));
                dimensions.add(cursor.getString(3));
                auton_pieces.add(cursor.getString(4));
                auton_line.add(cursor.getString(5));
                auton_center.add(cursor.getString(6));
                teleop_low.add(cursor.getString(7));
                teleop_mid.add(cursor.getString(8));
                teleop_high.add(cursor.getString(9));
                teleop_climb.add(cursor.getString(10));
                teleop_floor.add(cursor.getString(11));
                teleop_substation.add(cursor.getString(12));
                teleop_cubes.add(cursor.getString(13));
                teleop_cones.add(cursor.getString(14));
                image_filepath.add(cursor.getString(15));
                language.add(cursor.getString(16));

            }
            while(cursor.moveToNext()); //TODO make it strings and not ids

        }
        //converting to ints  -1 as the ids start at 1 and the table starts at 0
        Log.d("path123", String.valueOf(pit_id));
        teamNum_int = Integer.parseInt(teamNum.get(pit_id-1).toString());
        teamName_string = teamName.get(pit_id-1).toString();
        dimensions_int = Integer.parseInt(dimensions.get(pit_id-1).toString());
        auton_pieces_int = Integer.parseInt(auton_pieces.get(pit_id-1).toString());
        auton_line_int  = Integer.parseInt(auton_line.get(pit_id-1).toString());
        auton_center_int = Integer.parseInt(auton_center.get(pit_id-1).toString());
        teleop_low_int = Integer.parseInt(teleop_low.get(pit_id-1).toString());
        teleop_mid_int = Integer.parseInt(teleop_mid.get(pit_id-1).toString());
        teleop_high_int = Integer.parseInt(teleop_high.get(pit_id-1).toString());
        teleop_climb_int = Integer.parseInt(teleop_climb.get(pit_id-1).toString());
        teleop_floor_int = Integer.parseInt(teleop_floor.get(pit_id-1).toString());
        teleop_substation_int = Integer.parseInt(teleop_substation.get(pit_id-1).toString());
        teleop_cubes_int = Integer.parseInt(teleop_cubes.get(pit_id-1).toString());
        teleop_cones_int = Integer.parseInt(teleop_cones.get(pit_id-1).toString());
        language_string = language.get(pit_id-1).toString();

    }

    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, String.valueOf(teamNum_int) + ".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.imageView2);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
