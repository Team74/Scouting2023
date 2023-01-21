package com.example.frcscoutingapp2023;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    EditText cone_update_et, cubes_update_et;
    CheckBox balance_update_cb;
    Button update_button;

    String id, cones, cubes, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        cone_update_et = findViewById(R.id.coneInput_et);
        cubes_update_et = findViewById(R.id.cubeInput_update_et);
        balance_update_cb = findViewById(R.id.Balance_update_cb);
        update_button = findViewById(R.id.addData_update_btn);

        getAndSetIntentData();

        //set action bar title
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle(id);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateData.this);
                cones = cone_update_et.getText().toString().trim();
                cubes = cubes_update_et.getText().toString().trim();
                if(balance_update_cb.isChecked())
                {
                    balance = "1";
                }else
                {
                    balance = "0";
                }
                //Then we can call this
                myDB.updateData("0", "0",id, cones, cubes, balance);
            }
        });
        //First we call this


    }

    void getAndSetIntentData()//TODO make sure it works with partial data
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("cones") && getIntent().hasExtra("cubes")
                && getIntent().hasExtra("balance"))
        {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            cones = getIntent().getStringExtra("cones");
            cubes = getIntent().getStringExtra("cubes");
            balance = getIntent().getStringExtra("balance");

            //Setting intent from data
            cone_update_et.setText(cones);
            cubes_update_et.setText(cubes);
            if(balance.equals("1"))
            {
                balance_update_cb.setChecked(true);
            }else{
                balance_update_cb.setChecked(false);
            }


        }else{
            Toast.makeText(this, "No or Partial Data", Toast.LENGTH_SHORT).show();
        }
    }
}