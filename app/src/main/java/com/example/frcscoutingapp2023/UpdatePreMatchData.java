package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdatePreMatchData extends AppCompatActivity {

    int matchId;
    int matchNumInt, teamNumInt, autoConesLowInt, autoConesMidInt, autoConesHighInt, autoCubesLowInt, autoCubesMidInt, autoCubesHighInt, autoBalanceInt,
    teleConesLowInt, teleConesMidInt, teleConesHighInt, teleCubesLowInt, teleCubesMidInt, teleCubesHighInt, teleBalanceInt;
    
    ArrayList al_matchNum, al_teamNum, al_autoConesLow, al_autoConesMid, al_autoConesHigh, al_autoCubesLow, al_autoCubesMid, al_autoCubesHigh, al_autoBalance,
            al_teleConesLow, al_teleConesMid, al_teleConesHigh, al_teleCubesLow, al_teleCubesMid, al_teleCubesHigh, al_teleBalance;

    MyDataBaseHelper myDB = new MyDataBaseHelper(UpdatePreMatchData.this);

    EditText teamNumInput_et, matchNumInput_et;
    Button nextButton;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prematch_data);

        //region setting array lists
        al_matchNum = new ArrayList<>();
        al_teamNum = new ArrayList<>();
        al_autoConesLow = new ArrayList<>();
        al_autoConesMid = new ArrayList<>();
        al_autoConesHigh = new ArrayList<>();
        al_autoCubesLow = new ArrayList<>();
        al_autoCubesMid = new ArrayList<>();
        al_autoCubesHigh = new ArrayList<>();
        al_autoBalance = new ArrayList<>();

        al_teleConesLow = new ArrayList<>();
        al_teleConesMid = new ArrayList<>();
        al_teleConesHigh = new ArrayList<>();
        al_teleCubesLow = new ArrayList<>();
        al_teleCubesMid = new ArrayList<>();
        al_teleCubesHigh = new ArrayList<>();
        al_teleBalance = new ArrayList<>();
        //endregion

        teamNumInput_et = findViewById(R.id.team1_input_et);
        matchNumInput_et = findViewById(R.id.matchNum_input_et);
        nextButton = findViewById(R.id.toAutonButton);

        getIntentData();

        teamNumInput_et.setText(String.valueOf(teamNumInt));
        matchNumInput_et.setText(String.valueOf(matchNumInt));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((TextUtils.isEmpty(teamNumInput_et.getText().toString())) || (TextUtils.isEmpty(matchNumInput_et.getText().toString())))
                {
                    FailedToast();
                }else
                {
                    int teamNumInt = Integer.parseInt(teamNumInput_et.getText().toString());
                    int matchNumInt = Integer.parseInt(matchNumInput_et.getText().toString());

                    Intent intent = new Intent(UpdatePreMatchData.this, UpdateAutonData.class);
                    intent.putExtra("TeamNum", teamNumInt);
                    intent.putExtra("MatchNum", matchNumInt);
                    intent.putExtra("MatchID", matchId);

                    intent.putExtra("autoConesLow", autoConesLowInt);
                    intent.putExtra("autoConesMid", autoConesMidInt);
                    intent.putExtra("autoConesHigh", autoConesHighInt);
                    intent.putExtra("autoCubesLow", autoCubesLowInt);
                    intent.putExtra("autoCubesMid", autoCubesMidInt);
                    intent.putExtra("autoCubesHigh", autoCubesHighInt);

                    intent.putExtra("teleConesLow", teleConesLowInt);
                    intent.putExtra("teleConesMid", teleConesMidInt);
                    intent.putExtra("teleConesHigh", teleConesHighInt);
                    intent.putExtra("teleCubesLow", teleCubesLowInt);
                    intent.putExtra("teleCubesMid", teleCubesMidInt);
                    intent.putExtra("teleCubesHigh", teleCubesHighInt);

                    intent.putExtra("autoBalance", autoBalanceInt);
                    intent.putExtra("teleBalance", teleBalanceInt);
                    activity.startActivityForResult(intent, 1);
                }
            }
        });
    }


    void getIntentData()
    {
        matchId = getIntent().getIntExtra("id", 0);

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            cursor.moveToFirst();
            do
            {
                //syncs the array strings with the right column, make sure the ids match!
                al_matchNum.add(cursor.getString(1));
                al_teamNum.add(cursor.getString(2));
                al_autoConesLow.add(cursor.getString(3));
                al_autoConesMid.add(cursor.getString(4));
                al_autoConesHigh.add(cursor.getString(5));
                al_autoCubesLow.add(cursor.getString(7));
                al_autoCubesMid.add(cursor.getString(8));
                al_autoCubesHigh.add(cursor.getString(9));
                al_autoBalance.add(cursor.getString(11));

                al_teleConesLow.add(cursor.getString(12));
                al_teleConesMid.add(cursor.getString(13));
                al_teleConesHigh.add(cursor.getString(14));
                al_teleCubesLow.add(cursor.getString(16));
                al_teleCubesMid.add(cursor.getString(17));
                al_teleCubesHigh.add(cursor.getString(18));
                al_teleBalance.add(cursor.getString(20));
            }
            while(cursor.moveToNext()); //TODO make it strings and not ids

        }
        //converting to ints  -1 as the ids start at 1 and the table starts at 0
        matchNumInt = Integer.parseInt(al_matchNum.get(matchId-1).toString());
        teamNumInt = Integer.parseInt(al_teamNum.get(matchId-1).toString());
        
        autoConesLowInt = Integer.parseInt(al_autoConesLow.get(matchId-1).toString());
        autoConesMidInt = Integer.parseInt(al_autoConesMid.get(matchId-1).toString());
        autoConesHighInt = Integer.parseInt(al_autoConesHigh.get(matchId-1).toString());
        autoCubesLowInt = Integer.parseInt(al_autoCubesLow.get(matchId-1).toString());
        autoCubesMidInt = Integer.parseInt(al_autoCubesMid.get(matchId-1).toString());
        autoCubesHighInt = Integer.parseInt(al_autoCubesHigh.get(matchId-1).toString());

        teleConesLowInt = Integer.parseInt(al_teleConesLow.get(matchId-1).toString());
        teleConesMidInt = Integer.parseInt(al_teleConesMid.get(matchId-1).toString());
        teleConesHighInt = Integer.parseInt(al_teleConesHigh.get(matchId-1).toString());
        teleCubesLowInt = Integer.parseInt(al_teleCubesLow.get(matchId-1).toString());
        teleCubesMidInt = Integer.parseInt(al_teleCubesMid.get(matchId-1).toString());
        teleCubesHighInt = Integer.parseInt(al_teleCubesHigh.get(matchId-1).toString());
        Log.d("testing123", String.valueOf(autoCubesLowInt));
        
    }

    void FailedToast()
    {
        Toast.makeText(this, "Please enter data into all boxes", Toast.LENGTH_SHORT).show();
    }
}