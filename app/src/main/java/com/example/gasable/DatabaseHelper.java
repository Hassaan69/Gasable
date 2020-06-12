package com.example.gasable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";


    public static final String KEY_ID = "id";


    public static final String KEY_USER_NAME = "username";
    public static final String KEY_NAME = "name";
    public static final String KEY_MOBILE_NUMBER = "phone";



    public static final String KEY_EMAIL = "email";

    public static final String KEY_PASSWORD = "password";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT,"
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_MOBILE_NUMBER + " TEXT"
            + " ) ";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PASSWORD, user.name);
        values.put(KEY_USER_NAME, user.username);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_PASSWORD, user.password);
        values.put(KEY_PASSWORD, user.mobilenumber);


        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_NAME,KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD,KEY_MOBILE_NUMBER},
                KEY_USER_NAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        return null;
    }

    public boolean isExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_NAME, KEY_USER_NAME, KEY_EMAIL,KEY_PASSWORD,KEY_MOBILE_NUMBER},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{username},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}

