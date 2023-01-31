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

public class UpdateTeleOpData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView teamNum_tv;
    String teamName;
    Button toTeleOp;
    Spinner teamBalance;
    int matchID;

    TextView highCone1, midCone1, lowCone1, highCube1, midCube1, lowCube1;

    //name is locationType_plus/minus_team#_btn
    //this is for team 1
    Button highCone_minus_1_btn, midCone_minus_1_btn, lowCone_minus_1_btn, highCube_minus_1_btn, midCube_minus_1_btn, lowCube_minus_1_btn,
            highCone_plus_1_btn, midCone_plus_1_btn, lowCone_plus_1_btn, highCube_plus_1_btn, midCube_plus_1_btn, lowCube_plus_1_btn;

    int highConeInt1 = 0, midConeInt1 = 0, lowConeInt1 = 0, highCubeInt1 = 0, midCubeInt1 = 0, lowCubeInt1 = 0;
    int team1BalanceInt;

    Activity activity = UpdateTeleOpData.this; //make sure the activity is defined


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tele_op_data);

        teamNum_tv = findViewById(R.id.team1Num_txt);

        teamBalance = findViewById(R.id.autoTeam1Balance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.balance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamBalance.setAdapter(adapter);

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

        toTeleOp = findViewById(R.id.toTeleOp_btn);

        highCone1.setText(String.valueOf(highConeInt1));

        getAndSetIntentData();

        highCone1.setText(String.valueOf(highConeInt1));
        midCone1.setText(String.valueOf(midConeInt1));
        lowCone1.setText(String.valueOf(lowConeInt1));
        highCube1.setText(String.valueOf(highCubeInt1));
        midCube1.setText(String.valueOf(midCubeInt1));
        lowCube1.setText(String.valueOf(lowCubeInt1));


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

        teamBalance.setOnItemSelectedListener(this);

        toTeleOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int team1Num = getIntent().getIntExtra("Team1Num", 0);
                int matchNum = getIntent().getIntExtra("MatchNum", 0);
                int autoLowConeInt = getIntent().getIntExtra("autoConesLow", 0);
                int autoMidConeInt = getIntent().getIntExtra("autoConesMid", 0);
                int autoHighConeInt = getIntent().getIntExtra("autoConesHigh", 0);
                int autoLowCubeInt = getIntent().getIntExtra("autoCubesLow", 0);
                int autoMidCubeInt = getIntent().getIntExtra("autoCubesMid", 0);
                int autoHighCubeInt = getIntent().getIntExtra("autoCubesHigh", 0);
                int autoTeamBalanceInt = getIntent().getIntExtra("autoBalance", 0);

                Intent intent = new Intent(UpdateTeleOpData.this, UpdatePostMatchData.class);
                intent.putExtra("Team1Num", team1Num);
                intent.putExtra("MatchNum", matchNum);
                intent.putExtra("MatchID", matchID);

                intent.putExtra("teleHighCones1", highConeInt1);
                intent.putExtra("teleMidCones1", midConeInt1);
                intent.putExtra("teleLowCones1", lowConeInt1);
                intent.putExtra("teleHighCubes1", highCubeInt1);
                intent.putExtra("teleMidCubes1", midCubeInt1);
                intent.putExtra("teleLowCubes1", lowCubeInt1);
                intent.putExtra("teleBalance1", team1BalanceInt);

                intent.putExtra("autoHighCones1", autoHighConeInt);
                intent.putExtra("autoMidCones1", autoMidConeInt);
                intent.putExtra("autoConesLow", autoLowConeInt);
                intent.putExtra("autoHighCubes1", autoHighCubeInt);
                intent.putExtra("autoMidCubes1", autoMidCubeInt);
                intent.putExtra("autoLowCubes1", autoLowCubeInt);
                intent.putExtra("autoBalance1", autoTeamBalanceInt);

                activity.startActivityForResult(intent, 1);
            }
        });

    }


    void getAndSetIntentData()//TODO make sure it works with partial data
    {
        if(getIntent().hasExtra("MatchNum")) //TODO clean up
        {
            //Setting intent from data
            teamName = String.valueOf(getIntent().getIntExtra("Team1Num", 0));
            teamNum_tv.setText(teamName);

            lowConeInt1 = getIntent().getIntExtra("teleLowCones1", 0);
            midConeInt1 = getIntent().getIntExtra("teleMidCones1", 0);
            highConeInt1 = getIntent().getIntExtra("teleHighCones1", 0);
            lowCubeInt1 = getIntent().getIntExtra("teleLowCubes1", 0);
            midCubeInt1 = getIntent().getIntExtra("teleMidCubes1", 0);
            highCubeInt1 = getIntent().getIntExtra("teleHighCubes1", 0);
            team1BalanceInt = getIntent().getIntExtra("teleBalance1", 0);

            matchID = getIntent().getIntExtra("MatchID", 0);

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