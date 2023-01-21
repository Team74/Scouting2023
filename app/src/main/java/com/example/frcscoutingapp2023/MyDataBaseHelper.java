package com.example.frcscoutingapp2023;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    //read the read me for info on how to add columns

    private  Context context;
    private static final String DATABASE_NAME = "FRC_Scouting.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Match_Data";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MATCHNUM = "match_num";
    private static final String COLUMN_TEAMNUM = "team_num";

    //region Auto Cones
    private static final String COLUMN_autoConesLow = "autoConesLow";
    private static final String COLUMN_autoConesMid = "autoConesMid";
    private static final String COLUMN_autoConesHigh = "autoConesHigh";
    private static final String COLUMN_autoConesTotal = "autoConesTotal";
    //endregion

    //region Auto Cubes
    private static final String COLUMN_autoCubesLow = "autoCubesLow";
    private static final String COLUMN_autoCubesMid = "autoCubesMid";
    private static final String COLUMN_autoCubesHigh = "autoCubesHigh";
    private static final String COLUMN_autoCubesTotal = "autoCubesTotal";
    //endregion

    private static final String COLUMN_autoBalance = "autoBalance";

    //region TeleOp Cones
    private static final String COLUMN_teleOpConesLow = "teleOpConesLow";
    private static final String COLUMN_teleOpConesMid = "teleOpConesMid";
    private static final String COLUMN_teleOpConesHigh = "teleOpConesHigh";
    private static final String COLUMN_teleOpConesTotal = "teleOpConesTotal";
    //endregion

    //region TeleOp Cubes
    private static final String COLUMN_teleOpCubesLow = "teleOpCubesLow";
    private static final String COLUMN_teleOpCubesMid = "teleOpCubesMid";
    private static final String COLUMN_teleOpCubesHigh = "teleOpCubesHigh";
    private static final String COLUMN_teleOpCubesTotal = "teleOpCubesTotal";
    //endregion

    private static final String COLUMN_teleOpBalance = "teleOpBalance";

    private static final String COLUMN_Defence = "Defence";



    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // 0  Keep in mind the ids! put the right id into the storeDataInArray() for the add string method
                        COLUMN_MATCHNUM + " INTEGER, " + // 1
                        COLUMN_TEAMNUM + " INTEGER, " + // 2

                        COLUMN_autoConesLow + " INTEGER, " + // 3
                        COLUMN_autoConesMid + " INTEGER, " + // 4
                        COLUMN_autoConesHigh + " INTEGER, " +  // 5
                        COLUMN_autoConesTotal + " INTEGER, " + //6
                        COLUMN_autoCubesLow + " INTEGER, " + //7
                        COLUMN_autoCubesMid + " INTEGER, " + // 8
                        COLUMN_autoCubesHigh + " INTEGER, " + // 9
                        COLUMN_autoCubesTotal + " INTEGER, " + // 10
                        COLUMN_autoBalance + " INTEGER, " + // 11

                        COLUMN_teleOpConesLow + " INTEGER, " + // 12
                        COLUMN_teleOpConesMid + " INTEGER, " + // 13
                        COLUMN_teleOpConesHigh + " INTEGER, " +  //14
                        COLUMN_teleOpConesTotal + " INTEGER, " + //15
                        COLUMN_teleOpCubesLow + " INTEGER, " + // 16
                        COLUMN_teleOpCubesMid + " INTEGER, " + // 17
                        COLUMN_teleOpCubesHigh + " INTEGER, " + //18
                        COLUMN_teleOpCubesTotal + " INTEGER, " + //19
                        COLUMN_teleOpBalance + " INTEGER, " + // 20

                        COLUMN_Defence + " INTEGER);"; // 21

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addMatch(int matchNum, int teamNum, int cones, int cubes, boolean balance)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_teleOpConesTotal, cones);
        cv.put(COLUMN_teleOpCubesTotal, cubes);
        cv.put(COLUMN_teleOpBalance, balance);
        cv.put(COLUMN_MATCHNUM, matchNum);
        cv.put(COLUMN_TEAMNUM, teamNum);


        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1)//failed
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String matchNum, String teamNum, String row_id, String cones, String cubes, String balance)
    {
        Log.d("Update123", "got data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_teleOpConesTotal, cones);
        cv.put(COLUMN_teleOpConesTotal, cubes);
        cv.put(COLUMN_teleOpBalance, balance);
        cv.put(COLUMN_MATCHNUM, matchNum);
        cv.put(COLUMN_TEAMNUM, teamNum);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1)
        {
            Toast.makeText(context, "Failed To Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
        }
    }
}
