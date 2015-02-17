package com.example.pik92_000.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by pik92_000 on 10.01.2015.
 */




public class db extends SQLiteOpenHelper {

    private static final String DB_NAME = "DocLog.db";
    private static final int DB_VER = 1;
    public static final String TABLE_NAME="DocList";
    public static final String UID="_id";
    public static final String RECEIVER ="receiver";
    //public static final String THEME = "theme";
    public static final String OUTNUM = "outnum";
    public static final String INPNUM = "inpnum";
    public static final String THEME = "theme";
    public static final String PHOTOURI = "uri";
    public static final String LAT = "lat";
    public static final String LONG = "long";


    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME +
            " ("
            + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RECEIVER + " VARCHAR(255)"
            + THEME + "VARCHAR(255)"
            + OUTNUM + "INT"
            + INPNUM + "INT"
            + PHOTOURI + "STRING"
            + LAT + "DOUBLE"
            + LONG + "DOUBLE)";

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

    public void addLetter(){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.execSQL("PRAGMA synchronous=OFF");
            db.setLockingEnabled(false);
            db.beginTransaction();
            //int newId = db.update(LETTER, getContentValues(letter), "id='" +letter.getId()+"'",null);
            //if (newId == 0){
            //    db.insert(LETTER, null, getContentValues(letter));
            //}
            db.setTransactionSuccessful();
        }
        catch (Exception e) {}

        finally {
            db.endTransaction();
            db.setLockingEnabled(true);
            db.execSQL("PRAGMA synchronous=NORMAL");
        }

    }

    private ContentValues getContentValues(MainActivity letter) {
        ContentValues values = new ContentValues();
        values.put(RECEIVER, String.valueOf(letter.txtReceiver));
        values.put(THEME, String.valueOf(letter.txtTheme));


        return values;
    }

    public ArrayList getLetters(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<> letters = new ArrayList();

        db.close();
        return letters;
    }


}
