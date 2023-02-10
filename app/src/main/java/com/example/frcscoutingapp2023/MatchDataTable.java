package com.example.frcscoutingapp2023;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatchDataTable extends ScoutingReportActivity{

    MyDataBaseHelper myDB;

    public class UpdateMatchDataTable implements ReportUpdateCommand {

        public void update(String orderBy, String maxMin, String orderType) {
            // get all the data records from the DB
            String columns[] = {myDB.COLUMN_TEAMNUM, "Max_autoConesTotal", "Max_autoCubesTotal","Max_teleOpConesTotal", "Max_teleOpCubesTotal"};
            String headings[] = {"Team #", "Max Auto Cones", "Max Auto Cubes", "Max Tele Cones", "Max Tele Cubes"};

            String query = "SELECT " + myDB.COLUMN_TEAMNUM +
                    " , ROUND(AVG(autoConesTotal), 2) AS Max_autoConesTotal " +
                    " , ROUND(AVG(autoCubesTotal), 2) AS Max_autoCubesTotal " +
                    " , ROUND(AVG(teleOpConesTotal), 2) AS Max_teleOpConesTotal " +
                    " , ROUND(AVG(teleOpCubesTotal), 2) AS Max_teleOpCubesTotal " +
                    " FROM " + myDB.TABLE_NAME +
                    " GROUP BY " + myDB.COLUMN_TEAMNUM +
                    " ORDER BY " + orderType + " " + orderBy + " ";

            SQLiteDatabase db = myDB.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.matchDataTableHeader,
                    headings, this, 10);

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
                    for (String col : columns) {
                        Integer idx = cursor.getColumnIndex(col);
                        if (idx >= 0) {
                            values.add(cursor.getString(idx));
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
        UpdateMatchDataTable updateMatchDataTable = new UpdateMatchDataTable();

        updateMatchDataTable.update("DESC", "MAX", "teleOpConesTotal");
    }


}