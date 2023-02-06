package com.diya.dementiacare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "DementiaCare";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN email
    public static final String KEY_EMAIL = "email";

    //COLUMN ContactName1
    public static final String KEY_CNA1 = "contactname1";

    //COLUMN ContactNo2
    public static final String KEY_CNO2 = "contactno2";

    //COLUMN ContactName2
    public static final String KEY_CNA2 = "contactname2";

    //COLUMN ContactNo1
    public static final String KEY_CNO1 = "contactno1";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";


    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_CNA1 + " TEXT, "
            + KEY_CNO1 + " TEXT, "
            + KEY_CNA2 + " TEXT, "
            + KEY_CNO2 + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        //Put contact1name in  @values
        values.put(KEY_CNA1, user.contact1name);

        //Put contact2name in  @values
        values.put(KEY_CNA2, user.contact2name);

        //Put contact1no in  @values
        values.put(KEY_CNO1, user.contact1no);

        //Put contact2no in  @values
        values.put(KEY_CNO2, user.contact2no);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_CNA1, KEY_CNO1, KEY_CNA2, KEY_CNO2},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_CNA1, KEY_CNO1, KEY_CNA2, KEY_CNO2},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
    public ArrayList<String> fetch(String useremail) {
        ArrayList<String> ar = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_CNA1, KEY_CNO1, KEY_CNA2, KEY_CNO2},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{useremail},
                null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
        String cna1 = cursor.getString(cursor.getColumnIndex(SqliteHelper.KEY_CNA1));
        String cna2 = cursor.getString(cursor.getColumnIndex(SqliteHelper.KEY_CNA2));
        String cno1 = cursor.getString(cursor.getColumnIndex(SqliteHelper.KEY_CNO1));
        String cno2 = cursor.getString(cursor.getColumnIndex(SqliteHelper.KEY_CNO2));
        String patient = cursor.getString(cursor.getColumnIndex(SqliteHelper.KEY_USER_NAME));
        ar.add(cna1);
        ar.add(cna2);
        ar.add(cno1);
        ar.add(cno2);
        ar.add(patient);
        cursor.close();}
        return ar;
    }
}
