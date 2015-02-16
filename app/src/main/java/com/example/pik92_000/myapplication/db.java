package com.example.pik92_000.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pik92_000 on 10.01.2015.
 */




public class db extends SQLiteOpenHelper {

    private static final String DB_NAME = "DocLog.db";
    private static final int DB_VER = 1;
    public static final String TABLE_NAME="doclist";
    public static final String UID="_id";
    public static final String RECEIVER ="receiver";

    private static final String SQL_CREATE_ENTRIES ="CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + RECEIVER + " VARCHAR(255));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;


    public db(Context context){
        super (context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Обновление с "+ oldVersion+ " на версию "+ newVersion);
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }


}
