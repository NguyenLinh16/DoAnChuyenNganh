package com.example.learnenglish.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HocNgonNgu.db";
    public static final int DATABASE_VERSION = 1;

    // User table
    public static final String TABLE_USER = "User";
    public static final String COLUMN_USER_ID = "ID_User";
    public static final String COLUMN_USER_HOTEN = "HoTen";
    public static final String COLUMN_USER_POINT = "Point";
    public static final String COLUMN_USER_EMAIL = "Email";
    public static final String COLUMN_USER_SDT = "SDT";
    public static final String COLUMN_USER_ROLE = "Role";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create User table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                COLUMN_USER_HOTEN + " TEXT," +
                COLUMN_USER_POINT + " INTEGER," +
                COLUMN_USER_EMAIL + " TEXT," +
                COLUMN_USER_SDT + " TEXT," +
                COLUMN_USER_ROLE + " INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade when version changes
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
