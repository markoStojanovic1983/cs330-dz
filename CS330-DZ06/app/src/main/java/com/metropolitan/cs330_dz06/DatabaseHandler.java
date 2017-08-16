package com.metropolitan.cs330_dz06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mare on 6/11/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    protected static final String DATABASE_NAME = "ispit.db";
    private static final String TABLE_NAME = "studenti";

    private String BROJ_INDEKSA = "broj_indeksa";
    private String IME = "ime";
    private String BROJ_BODOVA = "broj_bodova";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + "( "
                + BROJ_INDEKSA + " INTEGER PRIMARY KEY, "
                + IME + " TEXT, "
                + BROJ_BODOVA + " INTEGER) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);

        onCreate(db);
    }

}