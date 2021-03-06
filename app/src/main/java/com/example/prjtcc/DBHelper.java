package com.example.prjtcc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.prjtcc.Contract.*;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "edit.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_EDITLIST_TABLE = "CREATE TABLE " +
                EditEntry.TABLE_NAME + " (" +
                EditEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EditEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                EditEntry.COLUMN_CPF + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_EMAIL + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_SIZE + "BYNARY NOT NULL, " +
                EditEntry.COLUMN_PASS + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_PHONE + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_CEP + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_STREET + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_NUMBER + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_COMPLEMENT + "VARCHAR NOT NULL, " +
                EditEntry.COLUMN_TIMESTAMP + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_EDITLIST_TABLE);
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from editList where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + EditEntry.TABLE_NAME);
        onCreate(db);
    }
}
