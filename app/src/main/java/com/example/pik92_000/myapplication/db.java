package com.example.pik92_000.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by pik92_000 on 10.01.2015.
 */




public class db extends SQLiteOpenHelper {

    private static final String DB_NAME = "DocLog.db";
    private static final int DB_VER = 2;
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
            + TABLE_NAME + " ("
            + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RECEIVER + " VARCHAR(255),"
            + THEME + " VARCHAR(255),"
            + OUTNUM + " INT,"
            + INPNUM + " INT,"
            + PHOTOURI + " STRING,"
            + LAT + " DOUBLE,"
            + LONG + " DOUBLE)";

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

    public void addLetter(Letter letter){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.execSQL("PRAGMA synchronous=OFF");
            db.setLockingEnabled(false);
            db.beginTransaction();
            //Toast.makeText(MyApp.getAppContext(),rcv, Toast.LENGTH_LONG).show();
            int newId = db.update(TABLE_NAME, getContentValues(letter), "_id='" + UID +"'",null);
            if (newId == 0){
                db.insert(TABLE_NAME, null, getContentValues(letter));
            }
            db.setTransactionSuccessful();
        }
        catch (Exception e) {}

        finally {
            db.endTransaction();
            db.setLockingEnabled(true);
            db.execSQL("PRAGMA synchronous=NORMAL");
        }

    }

    private ContentValues getContentValues(Letter letter) {
        ContentValues values = new ContentValues();
        values.put(RECEIVER, letter.Receiver);
        values.put(THEME, letter.Theme);
        values.put(INPNUM, letter.InpNum);
        values.put(OUTNUM, letter.OutNum);
        values.put(LAT, letter.Lat);
        values.put(LONG, letter.Lng);
        return values;
    }

    public ArrayList<Letter> getLetters() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Letter> letters = new ArrayList<Letter>();
        Cursor eventsCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        if (eventsCursor.moveToFirst()) {
            do {
                Letter letter = new Letter();
                letter.Receiver = eventsCursor.getString(1);
                letter.Theme = eventsCursor.getString(2);
                letters.add(letter);
            } while (eventsCursor.moveToNext());
        }
        eventsCursor.close();
        db.close();
        return letters;
    }



}
