package com.example.prjtcc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.prjtcc.AlarmContract.*;

public class AlarmDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "alarm.db";
    public static final int DATABASE_VERSION = 1;
    public AlarmDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ALARMLIST_TABLE = "CREATE TABLE " +
                AlarmEntry.TABLE_NAME + " (" +
                AlarmEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AlarmEntry.COLUMN_MEDICINE + " TEXT NOT NULL, " +
                AlarmEntry.COLUMN_SCHEDULE + "TIME NOT NULL, " +
                AlarmEntry.COLUMN_DOSE + "VARCHAR NOT NULL, " +
                AlarmEntry.COLUMN_TIMESTAMP + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_ALARMLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AlarmEntry.TABLE_NAME);
        onCreate(db);
    }
}
