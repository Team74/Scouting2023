package com.example.frcscoutingapp2023;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    //read the read me for info on how to add columns

    private  Context context;

    private static final String DATABASE_NAME = "FRC_Scouting.db";
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "Match_Data";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MATCHNUM = "match_num";
    public static final String COLUMN_TEAMNUM = "team_num";

    //region Auto Cones
    public static final String COLUMN_autoConesLow = "autoConesLow";
    public static final String COLUMN_autoConesMid = "autoConesMid";
    public static final String COLUMN_autoConesHigh = "autoConesHigh";
    public static final String COLUMN_autoConesTotal = "autoConesTotal";
    //endregion

    //region Auto Cubes
    public static final String COLUMN_autoCubesLow = "autoCubesLow";
    public static final String COLUMN_autoCubesMid = "autoCubesMid";
    public static final String COLUMN_autoCubesHigh = "autoCubesHigh";
    public static final String COLUMN_autoCubesTotal = "autoCubesTotal";
    //endregion

    public static final String COLUMN_autoBalance = "autoBalance";

    //region TeleOp Cones
    public static final String COLUMN_teleOpConesLow = "teleOpConesLow";
    public static final String COLUMN_teleOpConesMid = "teleOpConesMid";
    public static final String COLUMN_teleOpConesHigh = "teleOpConesHigh";
    public static final String COLUMN_teleOpConesTotal = "teleOpConesTotal";
    //endregion6

    //region TeleOp Cubes
    public static final String COLUMN_teleOpCubesLow = "teleOpCubesLow";
    public static final String COLUMN_teleOpCubesMid = "teleOpCubesMid";
    public static final String COLUMN_teleOpCubesHigh = "teleOpCubesHigh";
    public static final String COLUMN_teleOpCubesTotal = "teleOpCubesTotal";
    //endregion

    public static final String COLUMN_teleOpBalance = "teleOpBalance";

    public static final String COLUMN_autonWorked = "autonWorked";
    public static final String COLUMN_Broke = "broke";
    public static final String COLUMN_Defense = "Defence";


    //Pit Scouting
    //TODO make these not all caps like the other ones
    public static final String PIT_TABLE_NAME = "Pit_Scouting_Data";
    public static final String COLUMN_PIT_ID = "_pit_id";
    public static final String COLUMN_PIT_TEAMNUM = "_pit_teamnum";
    public static final String COLUMN_PIT_NAME = "_pit_name";
    public static final String COLUMN_DIMENSIONS = "_pit_dimensions";
    public static final String COLUMN_AUTON_PIECES = "_pit_auton_pieces";
    public static final String COLUMN_AUTON_LINE = "_pit_auton_line";
    public static final String COLUMN_AUTON_CENTER = "_pit_auton_center";
    public static final String COLUMN_TELEOP_LOW = "_pit_teleop_low";
    public static final String COLUMN_TELEOP_MID = "_pit_teleop_mid";
    public static final String COLUMN_TELEOP_HIGH = "_pit_teleop_high";
    public static final String COLUMN_TELEOP_CLIMB = "_pit_teleop_climb";
    public static final String COLUMN_TELEOP_FLOOR = "_pit_teleop_floor";
    public static final String COLUMN_TELEOP_SUBSTATION = "_pit_teleop_substation";
    public static final String COLUMN_TELEOP_CUBES = "_pit_teleop_cubes";
    public static final String COLUMN_TELEOP_CONES = "_pit_teleop_cones";
    public static final String COLUMN_PIT_LANGUAGE = "_pit_language";
    public static final String COLUMN_IMAGE_FILEPATH = "file_path";

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

                        COLUMN_autonWorked + " INTEGER, " + // 21
                        COLUMN_Broke + " INTEGER, " + // 22
                        COLUMN_Defense + " INTEGER);"; // 23

        Log.d("path123", "table 1");
        db.execSQL(query);

        Log.d("path123", "table 2");
        String pitScoutingQuery = "CREATE TABLE " + PIT_TABLE_NAME +
                " (" + COLUMN_PIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // 0  Keep in mind the ids! put the right id into the storeDataInArray() for the add string method
                COLUMN_PIT_TEAMNUM + " INTEGER," + // 1
                COLUMN_PIT_NAME + " STRING," + // 1
                COLUMN_DIMENSIONS + " INTEGER, " + // 2
                COLUMN_AUTON_PIECES + " INTEGER, " + // 3
                COLUMN_AUTON_LINE + " INTEGER, " + // 4
                COLUMN_AUTON_CENTER + " INTEGER, " + // 5
                COLUMN_TELEOP_LOW + " INTEGER, " + // 6
                COLUMN_TELEOP_MID + " INTEGER, " + // 7
                COLUMN_TELEOP_HIGH + " INTEGER, " + // 8
                COLUMN_TELEOP_CLIMB + " INTEGER, " + // 9
                COLUMN_TELEOP_FLOOR + " INTEGER, " + // 10
                COLUMN_TELEOP_SUBSTATION + " INTEGER, " + // 11
                COLUMN_TELEOP_CUBES + " INTEGER, " + // 12
                COLUMN_TELEOP_CONES + " INTEGER, " + // 13
                COLUMN_IMAGE_FILEPATH + " STRING, " + //14
                COLUMN_PIT_LANGUAGE + " INTEGER);"; // 15

        Log.d("path123", "table 2");
        db.execSQL(pitScoutingQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PIT_TABLE_NAME);
        onCreate(db);
    }

    long addMatch(int matchNum, int teamNum,
                  int autoHighCones, int autoMidCones, int autoLowCones,
                  int autoHighCubes, int autoMidCubes, int autoLowCubes,
                  int teleHighCones, int teleMidCones, int teleLowCones,
                  int teleHighCubes, int teleMidCubes, int teleLowCubes,
                  int autoBalance, int teleBalance, int autonWorked, int broke, int defence) {
        long result = addMatch(matchNum, teamNum,
            autoHighCones, autoMidCones, autoLowCones,
            autoHighCubes, autoMidCubes, autoLowCubes,
            teleHighCones, teleMidCones, teleLowCones,
            teleHighCubes, teleMidCubes, teleLowCubes,
            autoBalance, teleBalance,
            true, autonWorked, broke, defence);  // we want to see a toast!
        return result;
    }

    long addMatch(int matchNum, int teamNum,
                  int autoHighCones, int autoMidCones, int autoLowCones,
                  int autoHighCubes, int autoMidCubes, int autoLowCubes,
                  int teleHighCones, int teleMidCones, int teleLowCones,
                  int teleHighCubes, int teleMidCubes, int teleLowCubes,
                  int autoBalance, int teleBalance,
                  boolean dispToast, int autonWorked, int broke, int defence) {
        Log.d("insertData", String.valueOf(teamNum));
        long result = addOrUpdateMatch(matchNum, teamNum,
                autoHighCones, autoMidCones, autoLowCones,
                autoHighCubes, autoMidCubes, autoLowCubes,
                teleHighCones, teleMidCones, teleLowCones,
                teleHighCubes, teleMidCubes, teleLowCubes,
                autoBalance, teleBalance,
                dispToast,  // user specified toasting
                -1,  // we want to do an insert
                autonWorked, broke, defence);
        return result;
    }

    long addOrUpdateMatch(int matchNum, int teamNum,
                  int autoHighCones, int autoMidCones, int autoLowCones,
                  int autoHighCubes, int autoMidCubes, int autoLowCubes,
                  int teleHighCones, int teleMidCones, int teleLowCones,
                  int teleHighCubes, int teleMidCubes, int teleLowCubes,
                  int autoBalance, int teleBalance,
                  boolean dispToast, int MatchID, int autonWorked, int broke, int defence)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int autoConesTotal = autoHighCones + autoMidCones + autoLowCones;
        int autoCubesTotal = autoHighCubes + autoMidCubes + autoLowCubes;

        int teleConesTotal = teleHighCones + teleMidCones + teleLowCones;
        int teleCubesTotal = teleHighCubes + teleMidCubes + teleLowCubes;

        cv.put(COLUMN_MATCHNUM, matchNum);
        cv.put(COLUMN_TEAMNUM, teamNum);

        cv.put(COLUMN_autoConesLow, autoLowCones);
        cv.put(COLUMN_autoConesMid, autoMidCones);
        cv.put(COLUMN_autoConesHigh, autoHighCones);
        cv.put(COLUMN_autoConesTotal, autoConesTotal);
        cv.put(COLUMN_autoCubesLow, autoLowCubes);
        cv.put(COLUMN_autoCubesMid, autoMidCubes);
        cv.put(COLUMN_autoCubesHigh, autoHighCubes);
        cv.put(COLUMN_autoCubesTotal, autoCubesTotal);
        cv.put(COLUMN_autoBalance, autoBalance);
        cv.put(COLUMN_teleOpConesLow, teleLowCones);
        cv.put(COLUMN_teleOpConesMid, teleMidCones);
        cv.put(COLUMN_teleOpConesHigh, teleHighCones);
        cv.put(COLUMN_teleOpConesTotal, teleConesTotal);
        cv.put(COLUMN_teleOpCubesLow, teleLowCubes);
        cv.put(COLUMN_teleOpCubesMid, teleMidCubes);
        cv.put(COLUMN_teleOpCubesHigh, teleHighCubes);
        cv.put(COLUMN_teleOpCubesTotal, teleCubesTotal);
        cv.put(COLUMN_teleOpBalance, teleBalance);
        cv.put(COLUMN_autonWorked, autonWorked);
        cv.put(COLUMN_Broke, broke);
        cv.put(COLUMN_Defense, defence);

        long result;

        // if a valid match id was passed in, do an update
        if (MatchID < 0) {
            result = db.insert(TABLE_NAME, null, cv);
        } else {
            String row_id = String.valueOf(MatchID);
            result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        }

        if (dispToast) {
            if (result == -1)//failed
            {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        }

        return result;
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

    Cursor readAllPitScoutingData()
    {
        String query = "SELECT * FROM " + PIT_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getAllTeamNumbers()
    {
        String query = "SELECT * FROM " + TABLE_NAME + " GROUP BY " + COLUMN_TEAMNUM + " ORDER BY " + COLUMN_TEAMNUM + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Float getTeamData(int teamNum, String col)
    {
        String query = "SELECT AVG(" + col + ") AS avg FROM " + TABLE_NAME+ " WHERE " + COLUMN_TEAMNUM + " = " + String.valueOf(teamNum);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Float avgVal = cursor.getFloat(0);
        return avgVal;
    }

    Cursor getCertainTeamData(int teamNum)
    {
        String query = "SELECT COLUMN_TEAMNUM, AVG(COLUMN_autoConesTotal) FROM TABLE_NAME GROUP BY COLUMN_TEAMNUM";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    long updateData(int MatchID, int matchNum, int teamNum,
                    int autoHighCones, int autoMidCones, int autoLowCones,
                    int autoHighCubes, int autoMidCubes, int autoLowCubes,
                    int teleHighCones, int teleMidCones, int teleLowCones,
                    int teleHighCubes, int teleMidCubes, int teleLowCubes,
                    int autoBalance, int teleBalance, int autonWorked, int broke, int defence)
    {
        Log.d("updateData", String.valueOf(teamNum));
        long result = addOrUpdateMatch(matchNum, teamNum,
                autoHighCones, autoMidCones, autoLowCones,
                autoHighCubes, autoMidCubes, autoLowCubes,
                teleHighCones, teleMidCones, teleLowCones,
                teleHighCubes, teleMidCubes, teleLowCubes,
                autoBalance, teleBalance,
                true,  // we want to see a toast!
                MatchID, // we want to update this match id
                autonWorked, broke, defence);
        return result;
    }

    void createSampleData()
    {
        // delete any old data
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 0, 0);

        int[] sampleTeamNumbers = { 1, 74, 56, 88, 5565 };  // some sample #s

        // we're going to generate some random ints
        Random r = new Random();

        // create a list of 40 team numbers.
        List<Integer> teamNumbers = new ArrayList<Integer>();

        // first get any team numbers that exist in the current team data
        int[] currentTeamNumbers = {};  // tbd !!! daoTeamData.getAllTeamNumbers();
        for(int current : currentTeamNumbers) {
            teamNumbers.add(Integer.valueOf(current));
        }

        // then include any numbers in our sampleTeamNumbers array not already in list
        for(int sample : sampleTeamNumbers) {
            Integer teamNumber = Integer.valueOf(sample);
            if (!teamNumbers.contains(teamNumber)) { // no  dupes!
                teamNumbers.add(teamNumber);
            }
        }

        // lastly, add random team numbers until we have 40
        while (teamNumbers.size() < 40) {
            Integer teamNumber = Integer.valueOf(r.nextInt(8000) + 1); // 1-8000
            if (!teamNumbers.contains(teamNumber)) { // no dupes!
                teamNumbers.add(teamNumber);
            }
        }

        // for each number create a name
        for(int teamNumber : teamNumbers) {
            // generate a random team name
            String[] name0 = {"", "The "};
            String[] name1 = {"Fighting ", "Flying ", "Byting ", "Blazing ", "Amazing ", "Soaring "};
            String[] name2 = {"", "Techo-", "Robo-", "Electro-", "Mechanical ", "Lightning ", "Gigga-", "Steel ", "Iron ", "Wired-", "Cyber "};
            String[] name3 = {"Ants", "Bees", "Cats", "Dogs", "Eagles", "Fish", "Bots", "Gears", "Pistons", "Wrenches", "Cogs", "Inventors"};
            String[] name4 = {"", " Team", " Group", " Squad", " Crew", " Force", " Alliance", " Club"};
            String teamName = name0[r.nextInt(r.nextInt(name0.length) + 1)] // skew toward front of name0 list
                    + ((r.nextInt(4) < 3) ? "" : name1[r.nextInt(name1.length)]) // 25% chance of having something from name1 list
                    + name2[r.nextInt(name2.length)]
                    + name3[r.nextInt(name3.length)]
                    + name4[r.nextInt(r.nextInt(r.nextInt(name4.length) + 1) + 1)];  // really skew toward front of name4 list
            if (teamNumber == 74) {
                teamName = "Team C.H.A.O.S.";
            }
            // TBD: it's just sample data, but still might be good to check for duplicate names
            // and generate a new one
        }

        // generate some random match data
        for(int matchNum=1; matchNum<=60; matchNum++) {

            // each match gets 6 team number
            for(int i=0; i<6; i++){
                // get random team number from our list
                int teamNum = teamNumbers.get(r.nextInt(teamNumbers.size()));
                // tbd check if it's been used for this match already

                // now add random match data for that team
                addMatch(matchNum
                        , teamNum
                        , r.nextInt(6) // int autoHighCones,
                        , r.nextInt(6) // int autoMidCones,
                        , r.nextInt(6) // int autoLowCones,
                        , r.nextInt(6) // int autoHighCubes,
                        , r.nextInt(6) // int autoMidCubes,
                        , r.nextInt(6) // int autoLowCubes,
                        , r.nextInt(6) // int teleHighCones,
                        , r.nextInt(6) // int teleMidCones,
                        , r.nextInt(6) // int teleLowCones,
                        , r.nextInt(6) // int teleHighCubes,
                        , r.nextInt(6) // int teleMidCubes,
                        , r.nextInt(6) // int teleLowCubes,
                        , r.nextInt(4) // int autoBalance,  // (r.nextInt(2)==0) ? false : true;
                        , r.nextInt(4) // int teleBalance
                        , false,
                        r.nextInt(2),
                        r.nextInt(2),
                        r.nextInt(3) - 1
                );
            }
        }

        Toast.makeText(context, "Sample Data Created", Toast.LENGTH_SHORT).show();
    }

    void addPitScoutingTeam(int MatchID, ContentValues cv, Boolean dispToast)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        long result;

        // if a valid match id was passed in, do an update
        if (MatchID <= 0) {
            result = db.insert(PIT_TABLE_NAME, null, cv);
        } else {
            String row_id = String.valueOf(MatchID);
            result = db.update(PIT_TABLE_NAME, cv, "_id=?", new String[]{row_id});
        }

        if (dispToast) {
            if (result == -1)//failed
            {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
