package com.example.itubeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "iTubeDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, fullname TEXT, username TEXT UNIQUE, password TEXT)");
        db.execSQL("CREATE TABLE playlist (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, video_url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS playlist");
        onCreate(db);
    }

    public boolean registerUser(String fullName, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", fullName);
        cv.put("username", username);
        cv.put("password", password);
        long result = -1;
        try {
            result = db.insertOrThrow("users", null, cv);
        } catch (SQLiteConstraintException e) {
            return false;
        }
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public void addVideoToPlaylist(String username, String videoUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("video_url", videoUrl);
        db.insert("playlist", null, cv);
    }

    public List<String> getPlaylist(String username) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT video_url FROM playlist WHERE username=?", new String[]{username});
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        return list;
    }
}

