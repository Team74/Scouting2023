package com.example.frcscoutingapp2023;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AutonInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView team1Num_tv, team2Num_tv, team3Num_tv;
    String team1Name, team2Name, team3Name ;
    Button toTeleOp;
    Spinner team1Balance, team2Balance, team3Balance;

    TextView highCone1, midCone1, lowCone1, highCube1, midCube1, lowCube1;
    TextView highCone2, midCone2, lowCone2, highCube2, midCube2, lowCube2;
    TextView highCone3, midCone3, lowCone3, highCube3, midCube3, lowCube3;

    //name is locationType_plus/minus_team#_btn
    //this is for team 1
    Button highCone_minus_1_btn, midCone_minus_1_btn, lowCone_minus_1_btn, highCube_minus_1_btn, midCube_minus_1_btn, lowCube_minus_1_btn,
            highCone_plus_1_btn, midCone_plus_1_btn, lowCone_plus_1_btn, highCube_plus_1_btn, midCube_plus_1_btn, lowCube_plus_1_btn;

    int highConeInt1 = 0, midConeInt1 = 0, lowConeInt1 = 0, highCubeInt1 = 0, midCubeInt1 = 0, lowCubeInt1 = 0;
    int team1BalanceInt;

    //this is for team 2
    Button highCone_minus_2_btn, midCone_minus_2_btn, lowCone_minus_2_btn, highCube_minus_2_btn, midCube_minus_2_btn, lowCube_minus_2_btn,
            highCone_plus_2_btn, midCone_plus_2_btn, lowCone_plus_2_btn, highCube_plus_2_btn, midCube_plus_2_btn, lowCube_plus_2_btn;

    int highConeInt2 = 0, midConeInt2 = 0, lowConeInt2 = 0, highCubeInt2 = 0, midCubeInt2 = 0, lowCubeInt2 = 0;
    int team2BalanceInt;

    //this is for team 3
    Button highCone_minus_3_btn, midCone_minus_3_btn, lowCone_minus_3_btn, highCube_minus_3_btn, midCube_minus_3_btn, lowCube_minus_3_btn,
            highCone_plus_3_btn, midCone_plus_3_btn, lowCone_plus_3_btn, highCube_plus_3_btn, midCube_plus_3_btn, lowCube_plus_3_btn;

    int highConeInt3 = 0, midConeInt3 = 0, lowConeInt3 = 0, highCubeInt3 = 0, midCubeInt3 = 0, lowCubeInt3 = 0;
    int team3BalanceInt;

    Activity activity = AutonInput.this; //make sure the activity is defined


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton_input);

        team1Num_tv = findViewById(R.id.team1Num_txt);
        team2Num_tv = findViewById(R.id.team2Num_txt);
        team3Num_tv = findViewById(R.id.team3Num_txt);

        team1Balance = findViewById(R.id.autoTeam1Balance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.balance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team1Balance.setAdapter(adapter);

        highCone_minus_1_btn = findViewById(R.id.HC_minus_1_btn);
        highCone_plus_1_btn = findViewById(R.id.HC_plus_1_btn);
        highCone1 = findViewById(R.id.HC_total_1_tv);

        midCone_minus_1_btn = findViewById(R.id.MC_minus_1_btn);
        midCone_plus_1_btn = findViewById(R.id.MC_plus_1_btn);
        midCone1 = findViewById(R.id.MC_total_1_tv);

        lowCone_minus_1_btn = findViewById(R.id.LC_minus_1_btn);
        lowCone_plus_1_btn = findViewById(R.id.LC_plus_1_btn);
        lowCone1 = findViewById(R.id.LC_total_1_tv);

        highCube_minus_1_btn = findViewById(R.id.HCu_minus_1_btn);
        highCube_plus_1_btn = findViewById(R.id.HCu_plus_1_btn);
        highCube1 = findViewById(R.id.HCu_total_1_tv);

        midCube_minus_1_btn = findViewById(R.id.MCu_minus_1_btn);
        midCube_plus_1_btn = findViewById(R.id.MCu_plus_1_btn);
        midCube1 = findViewById(R.id.MCu_total_1_tv);

        lowCube_minus_1_btn = findViewById(R.id.LCu_minus_1_btn);
        lowCube_plus_1_btn = findViewById(R.id.LCu_plus_1_btn);
        lowCube1 = findViewById(R.id.LCu_total_1_tv);
        
        //Team 2
        team2Balance = findViewById(R.id.autoTeam2Balance);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team2Balance.setAdapter(adapter);

        highCone_minus_2_btn = findViewById(R.id.HC_minus_2_btn);
        highCone_plus_2_btn = findViewById(R.id.HC_plus_2_btn);
        highCone2 = findViewById(R.id.HC_total_2_tv);

        midCone_minus_2_btn = findViewById(R.id.MC_minus_2_btn);
        midCone_plus_2_btn = findViewById(R.id.MC_plus_2_btn);
        midCone2 = findViewById(R.id.MC_total_2_tv);

        lowCone_minus_2_btn = findViewById(R.id.LC_minus_2_btn);
        lowCone_plus_2_btn = findViewById(R.id.LC_plus_2_btn);
        lowCone2 = findViewById(R.id.LC_total_2_tv);

        highCube_minus_2_btn = findViewById(R.id.HCu_minus_2_btn);
        highCube_plus_2_btn = findViewById(R.id.HCu_plus_2_btn);
        highCube2 = findViewById(R.id.HCu_total_2_tv);

        midCube_minus_2_btn = findViewById(R.id.MCu_minus_2_btn);
        midCube_plus_2_btn = findViewById(R.id.MCu_plus_2_btn);
        midCube2 = findViewById(R.id.MCu_total_2_tv);

        lowCube_minus_2_btn = findViewById(R.id.LCu_minus_2_btn);
        lowCube_plus_2_btn = findViewById(R.id.LCu_plus_2_btn);
        lowCube2 = findViewById(R.id.LCu_total_2_tv);
        
        //Team 3
        team3Balance = findViewById(R.id.autoTeam3Balance);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team3Balance.setAdapter(adapter);

        highCone_minus_3_btn = findViewById(R.id.HC_minus_3_btn);
        highCone_plus_3_btn = findViewById(R.id.HC_plus_3_btn);
        highCone3 = findViewById(R.id.HC_total_3_tv);

        midCone_minus_3_btn = findViewById(R.id.MC_minus_3_btn);
        midCone_plus_3_btn = findViewById(R.id.MC_plus_3_btn);
        midCone3 = findViewById(R.id.MC_total_3_tv);

        lowCone_minus_3_btn = findViewById(R.id.LC_minus_3_btn);
        lowCone_plus_3_btn = findViewById(R.id.LC_plus_3_btn);
        lowCone3 = findViewById(R.id.LC_total_3_tv);

        highCube_minus_3_btn = findViewById(R.id.HCu_minus_3_btn);
        highCube_plus_3_btn = findViewById(R.id.HCu_plus_3_btn);
        highCube3 = findViewById(R.id.HCu_total_3_tv);

        midCube_minus_3_btn = findViewById(R.id.MCu_minus_3_btn);
        midCube_plus_3_btn = findViewById(R.id.MCu_plus_3_btn);
        midCube3 = findViewById(R.id.MCu_total_3_tv);

        lowCube_minus_3_btn = findViewById(R.id.LCu_minus_3_btn);
        lowCube_plus_3_btn = findViewById(R.id.LCu_plus_3_btn);
        lowCube3 = findViewById(R.id.LCu_total_3_tv);

        toTeleOp = findViewById(R.id.toTeleOp_btn);

        highCone1.setText(String.valueOf(highConeInt1));

        getAndSetIntentData();

        highCone1.setText(String.valueOf(highConeInt1));
        midCone1.setText(String.valueOf(midConeInt1));
        lowCone1.setText(String.valueOf(lowConeInt1));
        highCube1.setText(String.valueOf(highCubeInt1));
        midCube1.setText(String.valueOf(midCubeInt1));
        lowCube1.setText(String.valueOf(lowCubeInt1));

        highCone2.setText(String.valueOf(highConeInt2));
        midCone2.setText(String.valueOf(midConeInt2));
        lowCone2.setText(String.valueOf(lowConeInt2));
        highCube2.setText(String.valueOf(highCubeInt2));
        midCube2.setText(String.valueOf(midCubeInt2));
        lowCube2.setText(String.valueOf(lowCubeInt2));

        highCone3.setText(String.valueOf(highConeInt3));
        midCone3.setText(String.valueOf(midConeInt3));
        lowCone3.setText(String.valueOf(lowConeInt3));
        highCube3.setText(String.valueOf(highCubeInt3));
        midCube3.setText(String.valueOf(midCubeInt3));
        lowCube3.setText(String.valueOf(lowCubeInt3));



        //USE FIND AND REPLACE!! so much faster. ctrl r or edit - find - replace
        //region Plus and Minus Buttons Cones 1

        highCone_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt1 = highConeInt1 + 1;
                highCone1.setText(String.valueOf(highConeInt1));
            }
        });
        
        highCone_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt1 = highConeInt1 - 1;
                highCone1.setText(String.valueOf(highConeInt1));
            }
        });

        midCone_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt1 = midConeInt1 + 1;
                midCone1.setText(String.valueOf(midConeInt1));
            }
        });

        midCone_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt1 = midConeInt1 - 1;
                midCone1.setText(String.valueOf(midConeInt1));
            }
        });

        lowCone_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt1 = lowConeInt1 + 1;
                lowCone1.setText(String.valueOf(lowConeInt1));
            }
        });

        lowCone_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt1 = lowConeInt1 - 1;
                lowCone1.setText(String.valueOf(lowConeInt1));
            }
        });
        
        //endregion

        //region Plus and Minus Buttons Cubes 1

        highCube_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt1 = highCubeInt1 + 1;
                highCube1.setText(String.valueOf(highCubeInt1));
            }
        });

        highCube_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt1 = highCubeInt1 - 1;
                highCube1.setText(String.valueOf(highCubeInt1));
            }
        });

        midCube_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt1 = midCubeInt1 + 1;
                midCube1.setText(String.valueOf(midCubeInt1));
            }
        });

        midCube_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt1 = midCubeInt1 - 1;
                midCube1.setText(String.valueOf(midCubeInt1));
            }
        });

        lowCube_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt1 = lowCubeInt1 + 1;
                lowCube1.setText(String.valueOf(lowCubeInt1));
            }
        });

        lowCube_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt1 = lowCubeInt1 - 1;
                lowCube1.setText(String.valueOf(lowCubeInt1));
            }
        });

        //endregion

        //region Plus and Minus Buttons Cones 2

        highCone_plus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt2 = highConeInt2 + 1;
                highCone2.setText(String.valueOf(highConeInt2));
            }
        });

        highCone_minus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt2 = highConeInt2 - 1;
                highCone2.setText(String.valueOf(highConeInt2));
            }
        });

        midCone_plus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt2 = midConeInt2 + 1;
                midCone2.setText(String.valueOf(midConeInt2));
            }
        });

        midCone_minus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt2 = midConeInt2 - 1;
                midCone2.setText(String.valueOf(midConeInt2));
            }
        });

        lowCone_plus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt2 = lowConeInt2 + 1;
                lowCone2.setText(String.valueOf(lowConeInt2));
            }
        });

        lowCone_minus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt2 = lowConeInt2 - 1;
                lowCone2.setText(String.valueOf(lowConeInt2));
            }
        });

        //endregion  1

        //region Plus and Minus Buttons Cubes 2

        highCube_plus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt2 = highCubeInt2 + 1;
                highCube2.setText(String.valueOf(highCubeInt2));
            }
        });

        highCube_minus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt2 = highCubeInt2 - 1;
                highCube2.setText(String.valueOf(highCubeInt2));
            }
        });

        midCube_plus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt2 = midCubeInt2 + 1;
                midCube2.setText(String.valueOf(midCubeInt2));
            }
        });

        midCube_minus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt2 = midCubeInt2 - 1;
                midCube2.setText(String.valueOf(midCubeInt2));
            }
        });

        lowCube_plus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt2 = lowCubeInt2 + 1;
                lowCube2.setText(String.valueOf(lowCubeInt2));
            }
        });

        lowCube_minus_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt2 = lowCubeInt2 - 1;
                lowCube2.setText(String.valueOf(lowCubeInt2));
            }
        });

        //endregion


        //region Plus and Minus Buttons Cones 3

        highCone_plus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt3 = highConeInt3 + 1;
                highCone3.setText(String.valueOf(highConeInt3));
            }
        });

        highCone_minus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt3 = highConeInt3 - 1;
                highCone3.setText(String.valueOf(highConeInt3));
            }
        });

        midCone_plus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt3 = midConeInt3 + 1;
                midCone3.setText(String.valueOf(midConeInt3));
            }
        });

        midCone_minus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt3 = midConeInt3 - 1;
                midCone3.setText(String.valueOf(midConeInt3));
            }
        });

        lowCone_plus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt3 = lowConeInt3 + 1;
                lowCone3.setText(String.valueOf(lowConeInt3));
            }
        });

        lowCone_minus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt3 = lowConeInt3 - 1;
                lowCone3.setText(String.valueOf(lowConeInt3));
            }
        });

        //endregion

        //region Plus and Minus Buttons Cubes 3

        highCube_plus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt3 = highCubeInt3 + 1;
                highCube3.setText(String.valueOf(highCubeInt3));
            }
        });

        highCube_minus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt3 = highCubeInt3 - 1;
                highCube3.setText(String.valueOf(highCubeInt3));
            }
        });

        midCube_plus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt3 = midCubeInt3 + 1;
                midCube3.setText(String.valueOf(midCubeInt3));
            }
        });

        midCube_minus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt3 = midCubeInt3 - 1;
                midCube3.setText(String.valueOf(midCubeInt3));
            }
        });

        lowCube_plus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt3 = lowCubeInt3 + 1;
                lowCube3.setText(String.valueOf(lowCubeInt3));
            }
        });

        lowCube_minus_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt3 = lowCubeInt3 - 1;
                lowCube3.setText(String.valueOf(lowCubeInt3));
            }
        });

        //endregion

        team1Balance.setOnItemSelectedListener(this);

        toTeleOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int team1Num = getIntent().getIntExtra("Team1Num", 0);
                int team2Num = getIntent().getIntExtra("Team2Num", 0);
                int team3Num = getIntent().getIntExtra("Team3Num", 0);
                int matchNum = getIntent().getIntExtra("MatchNum", 0);

                Intent intent = new Intent(AutonInput.this, TeleOpInput.class);
                intent.putExtra("Team1Num", team1Num);
                intent.putExtra("Team2Num", team2Num);
                intent.putExtra("Team3Num", team3Num);
                intent.putExtra("MatchNum", matchNum);

                intent.putExtra("autoHighCones1", highConeInt1);
                intent.putExtra("autoMidCones1", midConeInt1);
                intent.putExtra("autoLowCones1", lowConeInt1);
                intent.putExtra("autoHighCubes1", highCubeInt1);
                intent.putExtra("autoMidCubes1", midCubeInt1);
                intent.putExtra("autoLowCubes1", lowCubeInt1);
                intent.putExtra("autoBalance1", team1BalanceInt);

                intent.putExtra("autoHighCones2", highConeInt2);
                intent.putExtra("autoMidCones2", midConeInt2);
                intent.putExtra("autoLowCones2", lowConeInt2);
                intent.putExtra("autoHighCubes2", highCubeInt2);
                intent.putExtra("autoMidCubes2", midCubeInt2);
                intent.putExtra("autoLowCubes2", lowCubeInt2);
                intent.putExtra("autoBalance2", team2BalanceInt);

                intent.putExtra("autoHighCones3", highConeInt3);
                intent.putExtra("autoMidCones3", midConeInt3);
                intent.putExtra("autoLowCones3", lowConeInt3);
                intent.putExtra("autoHighCubes3", highCubeInt3);
                intent.putExtra("autoMidCubes3", midCubeInt3);
                intent.putExtra("autoLowCubes3", lowCubeInt3);
                intent.putExtra("autoBalance3", team3BalanceInt);

                Log.d("testing123", String.valueOf(lowCubeInt1));

                activity.startActivityForResult(intent, 1);
            }
        });

    }


    void getAndSetIntentData()//TODO make sure it works with partial data
    {
        if(getIntent().hasExtra("MatchNum")) //TODO clean up
        {
            //Setting intent from data
            team1Name = String.valueOf(getIntent().getIntExtra("Team1Num", 0));
            team1Num_tv.setText(team1Name);

            team2Name = String.valueOf(getIntent().getIntExtra("Team2Num", 0));
            team2Num_tv.setText(team2Name);

            team3Name = String.valueOf(getIntent().getIntExtra("Team3Num", 0));
            team3Num_tv.setText(team3Name);

        }else{
            Toast.makeText(this, "No or Partial Data", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { //TODO dont hard code the switch in
        String balanceType = adapterView.getItemAtPosition(i).toString();
        switch (balanceType){
            case "Did Not Attempt":
                team1BalanceInt = 0;
                break;
            case "Failed Attempt":
                team1BalanceInt = 1;
                break;
            case "Engaged":
                team1BalanceInt = 2;
                break;
            case "Docked":
                team1BalanceInt = 3;
                break;
            default:
                team1BalanceInt = 0;
        }
        Log.d("Balance", String.valueOf(team1BalanceInt));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}