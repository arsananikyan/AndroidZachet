package com.example.gtx660ti.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GTX660TI on 30.11.2016.
 */

abstract public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "zachetDB";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    abstract public void onCreate(SQLiteDatabase db);

    @Override
    abstract  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
