package com.example.frcscoutingapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        String columns[] = { myDB.COLUMN_TEAMNUM, "Max_teleOpConesTotal"};
        String headings[] = { "Team #", "Max Cones" };

        String query = "SELECT " + myDB.COLUMN_TEAMNUM +
                " , MAX(teleOpConesTotal) AS Max_teleOpConesTotal " +
                " FROM " + myDB.TABLE_NAME +
                " GROUP BY " + myDB.COLUMN_TEAMNUM +
                " ORDER BY team_num DESC ";

        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

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