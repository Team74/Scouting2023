package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TeamReview extends ScoutingReportActivity {

    MyDataBaseHelper myDB;
   int teamNumInput = 324;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_review);

        myDB = new MyDataBaseHelper(TeamReview.this);
        this.UpdateTable();
    }

    void UpdateTable() {
        // get all the data records from the DB
        String columns[] = { myDB.COLUMN_MATCHNUM, myDB.COLUMN_TEAMNUM, myDB.COLUMN_teleOpConesTotal };
        String headings[] = { "Match #", "Team #", "Total Telop Cones" };

        Cursor cursor = myDB.readAllData();

        // add the header strings as a row to our table
        AddHeaderStringsAsRowToReportTable(R.id.teamReviewTable,
                headings, null, 10);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            // create a data row for each data record returned from DB
            cursor.moveToFirst();
            do {
                // add each data value to an array of strings
                List<String> values = new ArrayList<String>();
                for(String col : columns) {
                    Integer idx = cursor.getColumnIndex(col);
                    if (idx >= 0) {
                        values.add(cursor.getString(idx));
                    }
                }
                String[] data = values.toArray(new String[0]);

                // add the data strings as a row to our table
                AddDataStringsAsRowToReportTable(R.id.teamReviewTable, data);
            }
            while(cursor.moveToNext());
        }
    }
}