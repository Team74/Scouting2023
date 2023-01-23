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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AutonInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView team1Num_tv, team2Num_tv, team3Num_tv;
    String team1Name, team2Name, team3Name ;
    Button toTeleOp;
    Spinner team1Balance;

    TextView highCone1, midCone1, lowCone1, highCube1, midCube1, lowCube1;

    //name is locationType_plus/minus_team#_btn
    //this is for team 1
    Button highCone_minus_1_btn, midCone_minus_1_btn, lowCone_minus_1_btn, highCube_minus_1_btn, midCube_minus_1_btn, lowCube_minus_1_btn,
            highCone_plus_1_btn, midCone_plus_1_btn, lowCone_plus_1_btn, highCube_plus_1_btn, midCube_plus_1_btn, lowCube_plus_1_btn;

    int highConeInt = 0, midConeInt = 0, lowConeInt = 0, highCubeInt = 0, midCubeInt = 0, lowCubeInt = 0;
    int team1BalanceInt;

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

        highCone_minus_1_btn = findViewById(R.id.HC_minus_btn);
        highCone_plus_1_btn = findViewById(R.id.HC_plus_btn);
        highCone1 = findViewById(R.id.HC_total_tv);

        midCone_minus_1_btn = findViewById(R.id.MC_minus_btn);
        midCone_plus_1_btn = findViewById(R.id.MC_plus_btn);
        midCone1 = findViewById(R.id.MC_total_tv);

        lowCone_minus_1_btn = findViewById(R.id.LC_minus_btn);
        lowCone_plus_1_btn = findViewById(R.id.LC_plus_btn);
        lowCone1 = findViewById(R.id.LC_total_tv);

        highCube_minus_1_btn = findViewById(R.id.HCu_minus_btn);
        highCube_plus_1_btn = findViewById(R.id.HCu_plus_btn);
        highCube1 = findViewById(R.id.HCu_total_tv);

        midCube_minus_1_btn = findViewById(R.id.MCu_minus_btn);
        midCube_plus_1_btn = findViewById(R.id.MCu_plus_btn);
        midCube1 = findViewById(R.id.MCu_total_tv);

        lowCube_minus_1_btn = findViewById(R.id.LCu_minus_btn);
        lowCube_plus_1_btn = findViewById(R.id.LCu_plus_btn);
        lowCube1 = findViewById(R.id.LCu_total_tv);

        toTeleOp = findViewById(R.id.toTeleOp_btn);

        highCone1.setText(String.valueOf(highConeInt));

        getAndSetIntentData();

        highCone1.setText(String.valueOf(highConeInt));
        midCone1.setText(String.valueOf(midConeInt));
        lowCone1.setText(String.valueOf(lowConeInt));
        highCube1.setText(String.valueOf(highCubeInt));
        midCube1.setText(String.valueOf(midCubeInt));
        lowCube1.setText(String.valueOf(lowCubeInt));

        //USE FIND AND REPLACE!! so much faster. ctrl r or edit - find - replace
        //region Plus and Minus Buttons Cones

        highCone_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt = highConeInt + 1;
                highCone1.setText(String.valueOf(highConeInt));
            }
        });
        
        highCone_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highConeInt = highConeInt - 1;
                highCone1.setText(String.valueOf(highConeInt));
            }
        });

        midCone_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt = midConeInt + 1;
                midCone1.setText(String.valueOf(midConeInt));
            }
        });

        midCone_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midConeInt = midConeInt - 1;
                midCone1.setText(String.valueOf(midConeInt));
            }
        });

        lowCone_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt = lowConeInt + 1;
                lowCone1.setText(String.valueOf(lowConeInt));
            }
        });

        lowCone_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowConeInt = lowConeInt - 1;
                lowCone1.setText(String.valueOf(lowConeInt));
            }
        });
        
        //end region

        //region Plus and Minus Buttons Cubes

        highCube_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt = highCubeInt + 1;
                highCube1.setText(String.valueOf(highCubeInt));
            }
        });

        highCube_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highCubeInt = highCubeInt - 1;
                highCube1.setText(String.valueOf(highCubeInt));
            }
        });

        midCube_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt = midCubeInt + 1;
                midCube1.setText(String.valueOf(midCubeInt));
            }
        });

        midCube_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midCubeInt = midCubeInt - 1;
                midCube1.setText(String.valueOf(midCubeInt));
            }
        });

        lowCube_plus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt = lowCubeInt + 1;
                lowCube1.setText(String.valueOf(lowCubeInt));
            }
        });

        lowCube_minus_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowCubeInt = lowCubeInt - 1;
                lowCube1.setText(String.valueOf(lowCubeInt));
            }
        });

        //end region




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

                intent.putExtra("autoHighCones", highConeInt);
                intent.putExtra("autoMidCones", midConeInt);
                intent.putExtra("autoLowCones", lowConeInt);
                intent.putExtra("autoHighCubes", highCubeInt);
                intent.putExtra("autoMidCubes", midCubeInt);
                intent.putExtra("autoLowCubes", lowCubeInt);
                intent.putExtra("autoBalance", team1BalanceInt);

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