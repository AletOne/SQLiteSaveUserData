package com.example.wang.sqlitesaveuserdata.DBUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wang on 2/20/18.
 */

public class FavoriteTVDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "m_utv.db";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + FavoriteTVContact.UserEntry.TABLE_NAME + " (" +
                    FavoriteTVContact.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE + " TEXT," +
                    FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW + " TEXT)";


    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FavoriteTVContact.UserEntry.TABLE_NAME;


    public FavoriteTVDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
