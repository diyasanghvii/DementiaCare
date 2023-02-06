package com.diya.dementiacare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Dominic on 07/04/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "remind";
    public static final String TABLE_NAME = "tasks";
    public static final String C_ID = "_id";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String DETAIL = "description";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final int VERSION = 2;

    private final String createDB = "create table if not exists " + TABLE_NAME + " ( "
    + C_ID + " integer primary key autoincrement, "
    + TITLE + " text, "
    + DETAIL + " text, "
    + TYPE + " text, "
    + TIME + " text, "
    + DATE + " text)";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
    }

   public ArrayList<String> fetch(int id) {
       ArrayList<String> ar = new ArrayList<String>();
       Cursor cursor = this.db.query(DbHelper.TABLE_NAME,
               new String[]{DbHelper.C_ID, DbHelper.TITLE, DbHelper.DETAIL, DbHelper.TYPE, DbHelper.TIME, DbHelper.DATE},
               C_ID + "=?",
               new String[]{Integer.toString(id)},
               null, null, null, null);
       String titlee = cursor.getString(cursor.getColumnIndex(DbHelper.TITLE));
       String detaill = cursor.getString(cursor.getColumnIndex(DbHelper.DETAIL));
       ar.add(titlee);
       ar.add(detaill);
       return ar;
   }
}
