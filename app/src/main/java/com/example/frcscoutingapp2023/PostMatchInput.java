package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PostMatchInput extends AppCompatActivity{

    Button pushBtn;
    Activity activity = PostMatchInput.this;
    TextView team1Num_tv, team2Num_tv, team3Num_tv;
    Spinner team1Defence, team2Defence, team3Defence;
    int defenceType1, defenceType2, defenceType3;
    CheckBox autonWorked1_cb, autonWorked2_cb, autonWorked3_cb, broke1_cb, broke2_cb, broke3_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_match_input);
        
        pushBtn = findViewById(R.id.push_btn);
        team1Num_tv = findViewById(R.id.team1Num_tv);
        team2Num_tv = findViewById(R.id.team2Num_tv);
        team3Num_tv = findViewById(R.id.team3Num_tv);

        team1Defence = findViewById(R.id.team1Defence);
        team2Defence = findViewById(R.id.team2Defence);
        team3Defence = findViewById(R.id.team3Defence);

        autonWorked1_cb = findViewById(R.id.autonWorked1_cb);
        autonWorked2_cb = findViewById(R.id.autonWorked2_cb);
        autonWorked3_cb = findViewById(R.id.autonWorked3_cb);
        broke1_cb = findViewById(R.id.broke1_cb);
        broke2_cb = findViewById(R.id.broke2_cb);
        broke3_cb = findViewById(R.id.broke3_cb);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.defence, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team1Defence.setAdapter(adapter);
        team1Defence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String defenceType = adapterView.getItemAtPosition(i).toString();
                switch (defenceType){
                    case "None":
                        defenceType1 = 0;
                        break;
                    case "Defended":
                        defenceType1 = 1;
                        break;
                    case "Got Defended":
                        defenceType1 = 2;
                        break;
                    default:
                        defenceType1 = 0;
                }
                Log.d("Defence", String.valueOf(defenceType1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        team2Defence.setAdapter(adapter);
        team2Defence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String defenceType = adapterView.getItemAtPosition(i).toString();
                switch (defenceType){
                    case "None":
                        defenceType2 = 0;
                        break;
                    case "Defended":
                        defenceType2 = 1;
                        break;
                    case "Got Defended":
                        defenceType2 = 2;
                        break;
                    default:
                        defenceType2 = 0;
                }
                Log.d("Defence", String.valueOf(defenceType2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        team3Defence.setAdapter(adapter);
        team3Defence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String defenceType = adapterView.getItemAtPosition(i).toString();
                switch (defenceType){
                    case "None":
                        defenceType3 = 0;
                        break;
                    case "Defended":
                        defenceType3 = 1;
                        break;
                    case "Got Defended":
                        defenceType3 = 2;
                        break;
                    default:
                        defenceType3 = 0;
                }
                Log.d("Defence", String.valueOf(defenceType3));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getAndSetIntentData();

        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int team1Num = getIntent().getIntExtra("Team1Num", 0);
                int team2Num = getIntent().getIntExtra("Team2Num", 0);
                int team3Num = getIntent().getIntExtra("Team3Num", 0);
                int matchNum = getIntent().getIntExtra("MatchNum", 0);

                int autoHighCones1 = getIntent().getIntExtra("autoHighCones1", 0);
                int autoMidCones1 = getIntent().getIntExtra("autoMidCones1", 0);
                int autoLowCones1 = getIntent().getIntExtra("autoLowCones1", 0);
                int autoHighCubes1 = getIntent().getIntExtra("autoHighCubes1", 0);
                int autoMidCubes1 = getIntent().getIntExtra("autoMidCubes1", 0);
                int autoLowCubes1 = getIntent().getIntExtra("autoLowCubes1", 0);
                int autoBalance1 = getIntent().getIntExtra("autoBalance1", 0);

                int teleOpHighCones1 = getIntent().getIntExtra("teleOpHighCones1", 0);
                int teleOpMidCones1 = getIntent().getIntExtra("teleOpMidCones1", 0);
                int teleOpLowCones1 = getIntent().getIntExtra("teleOpLowCones1", 0);
                int teleOpHighCubes1 = getIntent().getIntExtra("teleOpHighCubes1", 0);
                int teleOpMidCubes1 = getIntent().getIntExtra("teleOpMidCubes1", 0);
                int teleOpLowCubes1 = getIntent().getIntExtra("teleOpLowCubes1", 0);
                int teleOpBalance1 = getIntent().getIntExtra("teleOpBalance1", 0);

                //region Checkboxs
                int autonWorked1;
                if(autonWorked1_cb.isChecked())
                {
                    autonWorked1 = 1;
                }else{
                    autonWorked1 = 0;
                }
                int broke1;
                if(broke1_cb.isChecked())
                {
                    broke1 = 1;
                }else{
                    broke1 = 0;
                }
                //endregion

                MyDataBaseHelper myDB = new MyDataBaseHelper(PostMatchInput.this);
                myDB.addMatch(matchNum, team1Num, autoHighCones1, autoMidCones1, autoLowCones1, autoHighCubes1, autoMidCubes1, autoLowCubes1, teleOpHighCones1,
                        teleOpMidCones1, teleOpLowCones1, teleOpHighCubes1, teleOpMidCubes1, teleOpLowCubes1, autoBalance1, teleOpBalance1, autonWorked1, broke1, defenceType1);

                int autoHighCones2 = getIntent().getIntExtra("autoHighCones2", 0);
                int autoMidCones2 = getIntent().getIntExtra("autoMidCones2", 0);
                int autoLowCones2 = getIntent().getIntExtra("autoLowCones2", 0);
                int autoHighCubes2 = getIntent().getIntExtra("autoHighCubes2", 0);
                int autoMidCubes2 = getIntent().getIntExtra("autoMidCubes2", 0);
                int autoLowCubes2 = getIntent().getIntExtra("autoLowCubes2", 0);
                int autoBalance2 = getIntent().getIntExtra("autoBalance2", 0);

                int teleOpHighCones2 = getIntent().getIntExtra("teleOpHighCones2", 0);
                int teleOpMidCones2 = getIntent().getIntExtra("teleOpMidCones2", 0);
                int teleOpLowCones2 = getIntent().getIntExtra("teleOpLowCones2", 0);
                int teleOpHighCubes2 = getIntent().getIntExtra("teleOpHighCubes2", 0);
                int teleOpMidCubes2 = getIntent().getIntExtra("teleOpMidCubes2", 0);
                int teleOpLowCubes2 = getIntent().getIntExtra("teleOpLowCubes2", 0);
                int teleOpBalance2 = getIntent().getIntExtra("teleOpBalance2", 0);

                //region Checkboxs
                int autonWorked2;
                if(autonWorked2_cb.isChecked())
                {
                    autonWorked2 = 1;
                }else{
                    autonWorked2 = 0;
                }
                int broke2;
                if(broke2_cb.isChecked())
                {
                    broke2 = 1;
                }else{
                    broke2 = 0;
                }
                //endregion
                
                myDB.addMatch(matchNum, team2Num, autoHighCones2, autoMidCones2, autoLowCones2, autoHighCubes2, autoMidCubes2, autoLowCubes2, teleOpHighCones2,
                        teleOpMidCones2, teleOpLowCones2, teleOpHighCubes2, teleOpMidCubes2, teleOpLowCubes2, autoBalance2, teleOpBalance2, autonWorked2, broke2, defenceType2);

                int autoHighCones3 = getIntent().getIntExtra("autoHighCones3", 0);
                int autoMidCones3 = getIntent().getIntExtra("autoMidCones3", 0);
                int autoLowCones3 = getIntent().getIntExtra("autoLowCones3", 0);
                int autoHighCubes3 = getIntent().getIntExtra("autoHighCubes3", 0);
                int autoMidCubes3 = getIntent().getIntExtra("autoMidCubes3", 0);
                int autoLowCubes3 = getIntent().getIntExtra("autoLowCubes3", 0);
                int autoBalance3 = getIntent().getIntExtra("autoBalance3", 0);

                int teleOpHighCones3 = getIntent().getIntExtra("teleOpHighCones3", 0);
                int teleOpMidCones3 = getIntent().getIntExtra("teleOpMidCones3", 0);
                int teleOpLowCones3 = getIntent().getIntExtra("teleOpLowCones3", 0);
                int teleOpHighCubes3 = getIntent().getIntExtra("teleOpHighCubes3", 0);
                int teleOpMidCubes3 = getIntent().getIntExtra("teleOpMidCubes3", 0);
                int teleOpLowCubes3 = getIntent().getIntExtra("teleOpLowCubes3", 0);
                int teleOpBalance3 = getIntent().getIntExtra("teleOpBalance3", 0);

                //region Checkboxs
                int autonWorked3;
                if(autonWorked3_cb.isChecked())
                {
                    autonWorked3 = 1;
                }else{
                    autonWorked3 = 0;
                }
                int broke3;
                if(broke3_cb.isChecked())
                {
                    broke3 = 1;
                }else{
                    broke3 = 0;
                }
                //endregion
                
                myDB.addMatch(matchNum, team3Num, autoHighCones3, autoMidCones3, autoLowCones3, autoHighCubes3, autoMidCubes3, autoLowCubes3, teleOpHighCones3,
                        teleOpMidCones3, teleOpLowCones3, teleOpHighCubes3, teleOpMidCubes3, teleOpLowCubes3, autoBalance3, teleOpBalance3, autonWorked3,broke3,defenceType3);

                Intent intent = new Intent(PostMatchInput.this, MatchView.class);
                activity.startActivityForResult(intent, 1);
                /*
                //region intent
                Intent intent = new Intent(PostMatchInput.this, TeleOpInput.class);
                intent.putExtra("Team1Num", team1Num);
                intent.putExtra("Team2Num", team2Num);
                intent.putExtra("Team3Num", team3Num);
                intent.putExtra("MatchNum", matchNum);

                intent.putExtra("autoHighCones1", autoHighCones1);
                intent.putExtra("autoMidCones1", autoMidCones1);
                intent.putExtra("autoLowCones1", autoLowCones1);
                intent.putExtra("autoHighCubes1", autoHighCubes1);
                intent.putExtra("autoMidCubes1", autoMidCubes1);
                intent.putExtra("autoLowCubes1", autoLowCubes1);
                intent.putExtra("autoBalance1", autoBalance1);

                intent.putExtra("teleOpHighCones1", teleOpHighCones1);
                intent.putExtra("teleOpMidCones1", teleOpMidCones1);
                intent.putExtra("teleOpLowCones1", teleOpLowCones1);
                intent.putExtra("teleOpHighCubes1", teleOpHighCubes1);
                intent.putExtra("teleOpMidCubes1", teleOpMidCubes1);
                intent.putExtra("teleOpLowCubes1", teleOpLowCubes1);
                intent.putExtra("teleOpBalance1", teleOpBalance1);

                intent.putExtra("autoHighCones2", autoHighCones2);
                intent.putExtra("autoMidCones2", autoMidCones2);
                intent.putExtra("autoLowCones2", autoLowCones2);
                intent.putExtra("autoHighCubes2", autoHighCubes2);
                intent.putExtra("autoMidCubes2", autoMidCubes2);
                intent.putExtra("autoLowCubes2", autoLowCubes2);
                intent.putExtra("autoBalance2", autoBalance2);

                intent.putExtra("teleOpHighCones2", teleOpHighCones2);
                intent.putExtra("teleOpMidCones2", teleOpMidCones2);
                intent.putExtra("teleOpLowCones2", teleOpLowCones2);
                intent.putExtra("teleOpHighCubes2", teleOpHighCubes2);
                intent.putExtra("teleOpMidCubes2", teleOpMidCubes2);
                intent.putExtra("teleOpLowCubes2", teleOpLowCubes2);
                intent.putExtra("teleOpBalance2", teleOpBalance2);

                intent.putExtra("autoHighCones3", autoHighCones3);
                intent.putExtra("autoMidCones3", autoMidCones3);
                intent.putExtra("autoLowCones3", autoLowCones3);
                intent.putExtra("autoHighCubes3", autoHighCubes3);
                intent.putExtra("autoMidCubes3", autoMidCubes3);
                intent.putExtra("autoLowCubes3", autoLowCubes3);
                intent.putExtra("autoBalance3", autoBalance3);

                intent.putExtra("teleOpHighCones3", teleOpHighCones3);
                intent.putExtra("teleOpMidCones3", teleOpMidCones3);
                intent.putExtra("teleOpLowCones3", teleOpLowCones3);
                intent.putExtra("teleOpHighCubes3", teleOpHighCubes3);
                intent.putExtra("teleOpMidCubes3", teleOpMidCubes3);
                intent.putExtra("teleOpLowCubes3", teleOpLowCubes3);
                intent.putExtra("teleOpBalance3", teleOpBalance3);
                //endregion
                   */
            }
        });
    }

    void getAndSetIntentData()//TODO make sure it works with partial data
    {
        if ((getIntent().hasExtra("MatchNum"))) //TODO clean up
        {
            //region Getting
            int team1Num = getIntent().getIntExtra("Team1Num", 0);
            int team2Num = getIntent().getIntExtra("Team2Num", 0);
            int team3Num = getIntent().getIntExtra("Team3Num", 0);
            int matchNum = getIntent().getIntExtra("MatchNum", 0);

            //endregion

            //region Setting

            team1Num_tv.setText(String.valueOf(team1Num));
            team2Num_tv.setText(String.valueOf(team2Num));
            team3Num_tv.setText(String.valueOf(team3Num));

            //endregion
        } else {
            Toast.makeText(this, "No or Partial Data", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed()
    {
        finish();
        Log.d("testing", "back button");
    }

    @Override
    public void finish()
    {
        Intent data = new Intent();
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}