package com.example.frcscoutingapp2023;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatchDataTable extends ScoutingReportActivity{

    MyDataBaseHelper myDB;
    String minMax = "AVG";
    Switch minMaxSwitch, advSimple;
    String advData = "";

    public class UpdateMatchDataTable implements ReportUpdateCommand {

        public void update(String orderBy, String orderType) {
            // get all the data records from the DB
            String simpleColumns[] = {myDB.COLUMN_TEAMNUM, "Max_autoConesTotal", "Max_autoCubesTotal","Max_teleOpConesTotal", "Max_teleOpCubesTotal"};
            String advColumns[] = {"MAX_autoBalance"};
            String simpleHeadings[] = {"Team #", "Avg Auto Cones", "Avg Auto Cubes", "Avg Tele Cones", "Avg Tele Cubes"};
            String advHeadings[] = {"Auton Balance"};

            String query = "SELECT " + myDB.COLUMN_TEAMNUM +
                    " , ROUND("+ minMax + "(autoConesTotal), 2) AS Max_autoConesTotal " +
                    " , ROUND("+ minMax + "(autoCubesTotal), 2) AS Max_autoCubesTotal " +
                    " , ROUND("+ minMax + "(teleOpConesTotal), 2) AS Max_teleOpConesTotal " +
                    " , ROUND("+ minMax + "(teleOpCubesTotal), 2) AS Max_teleOpCubesTotal " +
                    advData +
                    " FROM " + myDB.TABLE_NAME +
                    " GROUP BY " + myDB.COLUMN_TEAMNUM +
                    " ORDER BY " + orderType + " " + orderBy + " ";

            SQLiteDatabase db = myDB.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            if(advSimple.isChecked())// add the header strings as a row to our table
            {
                String[] headings = combine2Strings(simpleHeadings, advHeadings);
                Log.d("string array124", headings[4]);
                AddHeaderStringsAsRowToReportTable(R.id.matchDataTableHeader,
                        headings, this, 10);
            }else if(!advSimple.isChecked())
            {
                AddHeaderStringsAsRowToReportTable(R.id.matchDataTableHeader,
                        simpleHeadings, this, 10);
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
                    for (String col : simpleColumns) {
                        Integer idx = cursor.getColumnIndex(col);
                        if (idx >= 0) {
                            values.add(cursor.getString(idx));
                        }
                    }
                    if(advSimple.isChecked())
                    {
                        for (String col : advColumns) {
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

        myDB = new MyDataBaseHelper(MatchDataTable.this);
        minMaxSwitch = findViewById(R.id.switch1);
        advSimple = findViewById(R.id.advSimple);
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

        advSimple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(advSimple.isChecked())
                {
                    advData = ", ROUND((autoBalance), 2) AS MAX_autoBalance";
                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                }else if(!advSimple.isChecked())
                {
                    advData = "";
                    updateMatchDataTable.update("DESC", "teleOpConesTotal");
                }
            }
        });
    }

    String[] combine2Strings(String[] array1, String[] array2)
    {
        String [] result = new String[array1.length + array1.length];
        int index = 0;

        for (String item: array1) {
            result[index++] = item;
        }
        for (String item: array2) {
            result[index++] = item;
        }
        return result;
    }

}