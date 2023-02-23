package com.example.frcscoutingapp2023;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class MatchDataTable extends ScoutingReportActivity{

    MyDataBaseHelper myDB;
    String minMax = "AVG";
    Switch minMaxSwitch;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int radioIndex = 0;
    String simpleData = "";
    String advData = "";
    String allData = ""; //TODO Decide if its better to have all data show just the weird data or have all of the data just small

    //region TeleOp Points and Auton Points strings
    String totalTeleopPoints = "(teleOpConesLow * 2) + (teleOpConesMid * 3) + (teleOpConesHigh * 5) + (teleOpCubesLow * 2) + (teleOpCubesMid * 3) + (teleOpCubesHigh * 5)";
    
    String totalAutoPoints = "(autoConesLow * 2) + (autoConesMid * 3) + (autoConesHigh * 5) + (autoCubesLow * 2) + (autoCubesMid * 3) + (autoCubesHigh * 5)";
    //endregion

    public class UpdateMatchDataTable implements ReportUpdateCommand {

        public void update(String orderBy, String orderType) {
            // get all the data records from the DB
            String simpleColumns[] = {myDB.COLUMN_TEAMNUM, "Total_Points", "Max_autoPiecesTotal","Max_teleOpConesTotal", "Max_teleOpCubesTotal"};
            String advColumns[] = {"MAX_autoBalance", "_teleOpBalance", "_autoConesTotal", "_autoCubesTotal", "_autonWorked", "_broke", "_Defence"};
            String allColumns[] = {"_autoConesLow", "_autoConesMid", "_autoConesHigh", "_autoCubesLow", "_autoCubesMid", "_autoCubesHigh",
                    "_teleOpConesLow", "_teleOpConesMid", "_teleOpConesHigh", "_teleOpCubesLow", "_teleOpCubesMid", "_teleOpCubesHigh"};

            String simpleHeadings[] = {"Team #", "Total Points", "Avg Auto Cubes", "Avg Tele Cones", "Avg Tele Cubes"};
            String advHeadings[] = {"Auton Balance", "_teleOpBalance", "_autoConesTotal", "_autoCubesTotal", "_autonWorked", "_broke", "_Defence"};
            String allHeadings[] = {"Team #", "_autoConesLow", "_autoConesMid", "_autoConesHigh", "_autoCubesLow", "_autoCubesMid", "_autoCubesHigh",
                    "_teleOpConesLow", "_teleOpConesMid", "_teleOpConesHigh", "_teleOpCubesLow", "_teleOpCubesMid", "_teleOpCubesHigh"};

            String query = "SELECT " + myDB.COLUMN_TEAMNUM +
                    simpleData +
                    advData + allData +
                    " FROM " + myDB.TABLE_NAME +
                    " GROUP BY " + myDB.COLUMN_TEAMNUM +
                    " ORDER BY " + orderType + " " + orderBy + " ";

            SQLiteDatabase db = myDB.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            Log.d("testing123", "abc " + String.valueOf(radioIndex));
            if(radioIndex == 1)// add the header strings as a row to our table
            {
                String[] headings = combine2Strings(simpleHeadings, advHeadings);
                AddHeaderStringsAsRowToReportTable(R.id.matchDataTableHeader,
                        headings, this, 10);
            }else if(radioIndex == 0)
            {
                AddHeaderStringsAsRowToReportTable(R.id.matchDataTableHeader,
                        simpleHeadings, this, 10);
            } else if (radioIndex == 2) {
                AddHeaderStringsAsRowToReportTable(R.id.matchDataTableHeader,
                        allHeadings, this, 10);
            }


            if (cursor.getCount() == 0) {
                //Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            } else {
                TableLayout table = findViewById(R.id.matchDataTable);
                table.removeAllViews();
                // create a data row for each data record returned from DB
                cursor.moveToFirst();
                do {
                    // add each data value to an array of strings
                    List<String> values = new ArrayList<String>();
                    if(radioIndex == 0 || radioIndex ==1) {
                        for (String col : simpleColumns) {
                            Integer idx = cursor.getColumnIndex(col);
                            if (idx >= 0) {
                                values.add(cursor.getString(idx));
                            }
                        }
                    }
                    if(radioIndex == 1)
                    {
                        for (String col : advColumns) {
                            Integer idx = cursor.getColumnIndex(col);
                            if (idx >= 0) {
                                values.add(cursor.getString(idx));
                            }
                        }
                    }
                    if(radioIndex == 2)
                    {
                        for (String col : allColumns) {
                            Integer idx = cursor.getColumnIndex(col);
                            if (idx >= 0) {
                                values.add(cursor.getString(idx));
                            }
                        }
                    }
                    String[] data = values.toArray(new String[0]);

                    // add the data strings as a row to our table
                    AddDataStringsAsRowToReportTable(R.id.matchDataTable, data);
                }
                while (cursor.moveToNext());
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_data_table);

        simpleData = " , ROUND("+ minMax + " (" + totalTeleopPoints + "+" + totalAutoPoints + "), 2) AS Total_Points " +
                    " , ROUND("+ minMax + "(autoCubesTotal + autoConesTotal), 2) AS Max_autoPiecesTotal " +
                    " , ROUND("+ minMax + "(teleOpConesTotal), 2) AS Max_teleOpConesTotal " +
                    " , ROUND("+ minMax + "(teleOpCubesTotal), 2) AS Max_teleOpCubesTotal ";

        myDB = new MyDataBaseHelper(MatchDataTable.this);
        minMaxSwitch = findViewById(R.id.switch1);
        radioGroup = findViewById(R.id.radioGroup);
        UpdateMatchDataTable updateMatchDataTable = new UpdateMatchDataTable();

        updateMatchDataTable.update("DESC", "teleOpConesTotal");
        minMaxSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(minMaxSwitch.isChecked())
                {
                    minMax = "MAX";
                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                }else if(!minMaxSwitch.isChecked())
                {
                    minMax = "AVG";
                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioID);
                radioIndex = radioGroup.indexOfChild(radioButton);
                if(radioIndex == 1) //advance
                {
                    advData = ", ROUND(" + minMax + "(autoBalance), 2) AS MAX_autoBalance, ROUND(" + minMax + "(teleOpBalance), 2) AS _teleOpBalance, ROUND(" + minMax + "(autoConesTotal), 2) AS _autoConesTotal, " +
                            "ROUND(" + minMax + "(autoCubesTotal), 2) AS _autoCubesTotal, ROUND(" + minMax + "(autonWorked), 2) AS _autonWorked, ROUND(" + minMax + "(broke), 2) AS _broke, " +
                            "ROUND(" + minMax + "(Defence), 2) AS _Defence";

                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                } else if (radioIndex == 0) { //simple
                    advData = "";
                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                } else if (radioIndex == 2) { //all
                    advData = ", ROUND((autoBalance), 2) AS MAX_autoBalance, ROUND((teleOpBalance), 2) AS _teleOpBalance, ROUND((autoConesTotal), 2) AS _autoConesTotal, " +
                            "ROUND((autoCubesTotal), 2) AS _autoCubesTotal, ROUND((autonWorked), 2) AS _autonWorked, ROUND((broke), 2) AS _broke, " +
                            "ROUND((Defence), 2) AS _Defence";
                    allData = ", ROUND((autoConesLow), 2) AS _autoConesLow, ROUND((autoConesMid), 2) AS _autoConesMid, ROUND((autoConesHigh), 2) AS _autoConesHigh," +
                            "ROUND((autoCubesLow), 2) AS _autoCubesLow, ROUND((autoCubesMid), 2) AS _autoCubesMid, ROUND((autoCubesHigh), 2) AS _autoCubesHigh," +
                            "ROUND((teleOpConesLow), 2) AS _teleOpConesLow, ROUND((teleOpConesMid), 2) AS _teleOpConesMid, ROUND((teleOpConesHigh), 2) AS _teleOpConesHigh," +
                            "ROUND((teleOpCubesLow), 2) AS _teleOpCubesLow, ROUND((teleOpCubesMid), 2) AS _teleOpCubesMid, ROUND((teleOpCubesHigh), 2) AS _teleOpCubesHigh";

                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                }
                Log.d("testing123", String.valueOf(radioIndex));
            }
        });

    }

    String[] combine2Strings(String[] array1, String[] array2)
    {
        String [] result = new String[array1.length + array2.length];
        int index = 0;

        for (String item: array1) {
            if(item != null)
            {
                result[index++] = item;
            }
        }
        for (String item: array2) {
            if(item != null)
            {
                result[index++] = item;
            }
        }
        return result;
    }

}
