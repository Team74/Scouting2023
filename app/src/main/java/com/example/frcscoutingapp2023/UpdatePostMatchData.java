package com.example.frcscoutingapp2023;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UpdatePostMatchData extends AppCompatActivity {

    Button pushBtn;
    Activity activity = UpdatePostMatchData.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_match_input);

        pushBtn = findViewById(R.id.push_btn);

        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int team1Num = getIntent().getIntExtra("Team1Num", 0);
                int matchNum = getIntent().getIntExtra("MatchNum", 0);
                int matchId = getIntent().getIntExtra("MatchID", 0);

                int autoHighCones1 = getIntent().getIntExtra("autoConesHigh", 0);
                int autoMidCones1 = getIntent().getIntExtra("autoConesMid", 0);
                int autoLowCones1 = getIntent().getIntExtra("autoConesLow", 0);
                int autoHighCubes1 = getIntent().getIntExtra("autoCubesHigh", 0);
                int autoMidCubes1 = getIntent().getIntExtra("autoCubesMid", 0);
                int autoLowCubes1 = getIntent().getIntExtra("autoCubesLow", 0);
                int autoBalance1 = getIntent().getIntExtra("autoBalance", 0);

                int teleOpHighCones1 = getIntent().getIntExtra("teleConesHigh", 0);
                int teleOpMidCones1 = getIntent().getIntExtra("teleConesMid", 0);
                int teleOpLowCones1 = getIntent().getIntExtra("teleConesLow", 0);
                int teleOpHighCubes1 = getIntent().getIntExtra("teleCubesHigh", 0);
                int teleOpMidCubes1 = getIntent().getIntExtra("teleCubesMid", 0);
                int teleOpLowCubes1 = getIntent().getIntExtra("teleCubesLow", 0);
                int teleOpBalance1 = getIntent().getIntExtra("teleBalance", 0);

                Log.d("testing123", String.valueOf(autoLowCubes1));
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdatePostMatchData.this);
                myDB.updateData(matchId, matchNum, team1Num, autoHighCones1, autoMidCones1, autoLowCones1, autoHighCubes1, autoMidCubes1, autoLowCubes1, teleOpHighCones1,
                        teleOpMidCones1, teleOpLowCones1, teleOpHighCubes1, teleOpMidCubes1, teleOpLowCubes1, autoBalance1, teleOpBalance1, 0,0,0);

                Log.d("testing123", "Team Balance Int " + String.valueOf(autoBalance1));

                Intent intent = new Intent(UpdatePostMatchData.this, MatchView.class);
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