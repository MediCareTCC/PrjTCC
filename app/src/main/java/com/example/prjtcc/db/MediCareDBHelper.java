package com.example.prjtcc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.prjtcc.db.MediCareContract.*;

import androidx.annotation.Nullable;

public class MediCareDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MediCare.db";
    public static final int DATABASE_VERSION = 1;

    public MediCareDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MEDICARE_TABLE = "CREATE TABLE " +
                MediCareEntry.TABLE_NAME + " (" +
                MediCareEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MediCareEntry.COLUMN_CPF + " VARCHAR(14) NOT NULL, " +
                MediCareEntry.COLUMN_SENHA + " VARCHAR(64) NOT NULL, " +
                MediCareEntry.COLUMN_NOME + " VARCHAR(40) NOT NULL, " +
                MediCareEntry.COLUMN_EMAIL + " VARCHAR(64), " +
                MediCareEntry.COLUMN_TELEFONE + " VARCHAR(14), " +
                MediCareEntry.COLUMN_CEP + " VARCHAR(9), " +
                MediCareEntry.COLUMN_RUA + " VARCHAR(32), " +
                MediCareEntry.COLUMN_NUMERO + " VARCHAR(6), " +
                MediCareEntry.COLUMN_COMPLEMENTO + " VARCHAR(32) " +
                ");";

                db.execSQL(SQL_CREATE_MEDICARE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MediCareEntry.TABLE_NAME);
        onCreate(db);
    }
}
