package com.example.lostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFound.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "items";
    private static final String COL_ID = "id";
    private static final String COL_TYPE = "type";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "phone";
    private static final String COL_DESC = "description";
    private static final String COL_DATE = "date";
    private static final String COL_LOCATION = "location";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TYPE + " TEXT, " +
                COL_NAME + " TEXT, " +
                COL_PHONE + " TEXT, " +
                COL_DESC + " TEXT, " +
                COL_DATE + " TEXT, " +
                COL_LOCATION + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertItem(String type, String name, String phone, String desc, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TYPE, type);
        cv.put(COL_NAME, name);
        cv.put(COL_PHONE, phone);
        cv.put(COL_DESC, desc);
        cv.put(COL_DATE, date);
        cv.put(COL_LOCATION, location);
        db.insert(TABLE_NAME, null, cv);
    }

    public List<String> getAllItems() {
        List<String> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_ID + ", " + COL_TYPE + ", " + COL_DESC + " FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                items.add(cursor.getInt(0) + ": " + cursor.getString(1) + " " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public Cursor getItemById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
