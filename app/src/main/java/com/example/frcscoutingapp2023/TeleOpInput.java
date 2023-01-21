package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

//Screen with the data input for teleop
public class TeleOpInput extends AppCompatActivity {

    EditText conesInput, cubesInput, matchInput, teamInput;
    CheckBox balanceInput;
    Button pushButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop_input);

        matchInput = findViewById(R.id.match_input_et);
        teamInput = findViewById(R.id.team_input_et);
        conesInput = findViewById(R.id.coneInput_et);
        cubesInput = findViewById(R.id.cubeInput_et);
        balanceInput = findViewById(R.id.Balance_update_cb);
        pushButton = findViewById(R.id.addData_btn);

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //TODO make the input and string more different
                boolean canBalance = balanceInput.isChecked();

                String matchInputNum =  matchInput.getText().toString();
                int matchNum = Integer.parseInt(matchInputNum);

                String teamInputNum =  teamInput.getText().toString();
                int teamNum = Integer.parseInt(teamInputNum);

                //converting strings to ints
                String coneInput = conesInput.getText().toString();
                int numCone = Integer.parseInt(coneInput);

                String cubeInput = cubesInput.getText().toString();
                int numCube = Integer.parseInt(cubeInput);

                MyDataBaseHelper myDB = new MyDataBaseHelper(TeleOpInput.this);
                //cone, cube, balance
                myDB.addMatch(matchNum,teamNum,numCone,numCube,canBalance);
            }
        });
    }
}