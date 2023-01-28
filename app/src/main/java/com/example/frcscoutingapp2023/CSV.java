package com.example.frcscoutingapp2023;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    private  Context context;

    public CSV(@Nullable Context context) {
        this.context = context;
    }

    // files created programmatically in downloads folder may not
    // appear immediately to other system utilities.  This sends out
    // a notification to the system about it.
    private void makeCreatedFileVisibleInDownloads(String downloadFilename) {
        File downloadFile = new File(downloadFilename);
        String mimeType = "text/csv";
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.addCompletedDownload(downloadFile.getName(), downloadFilename, true,
                    mimeType, downloadFile.getAbsolutePath(), downloadFile.length(), true);
        }
    }

    // export MatchData to CSV
    public void exportMatchData(String baseDir) {
        try {
            MyDataBaseHelper myDB = new MyDataBaseHelper(this.context);

            Cursor cursor = myDB.readAllData();
            if(cursor.getCount() == 0) {
                Toast.makeText(this.context, "No Data", Toast.LENGTH_SHORT).show();
            } else {

                // makes the filepath
                String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                String filePath = baseDir + File.separator + "MatchData-" + androidId + ".csv";

                // creates file and attaches CSV writer to it
                CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));

                // write a header record to the CSV file to aid in importing into Tableau
                String[] csvHeaderLine = {
                        myDB.COLUMN_MATCHNUM,
                        myDB.COLUMN_TEAMNUM
                };
                writer.writeNext(csvHeaderLine);

                cursor.moveToFirst();
                do
                {
                    List<String> csvDataLine = new ArrayList<String>();
                    for(String col : csvHeaderLine) {
                        Integer idx = cursor.getColumnIndex(col);
                        if (idx >= 0) {
                            csvDataLine.add(cursor.getString(idx));
                        } else {
                            throw new Exception("bad column");
                        }
                    }

                    // write the CSV record to the file
                    String[] line = csvDataLine.toArray(new String[0]);
                    writer.writeNext(line);

                }
                while(cursor.moveToNext());

                // close the CSV file and "publish" it to the system
                writer.close();

                makeCreatedFileVisibleInDownloads(filePath);
                Toast.makeText(this.context, "MatchData CSV file successfully exported", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.context, "Error creating MatchData CSV file", Toast.LENGTH_SHORT).show();
        }
    }

}
