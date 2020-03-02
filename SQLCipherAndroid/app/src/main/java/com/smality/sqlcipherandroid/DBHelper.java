package com.smality.sqlcipherandroid;

import android.content.ContentValues;
import android.content.Context;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test.db";
    private static final String TABLE_NAME = "person";
    private static final String PASSWORD = "password";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context); //first init the db libraries with the context
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        db.execSQL("CREATE TABLE IF NOT EXISTS person" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, sex TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("update Database");
    }

    public void insert(ContentValues values){

        SQLiteDatabase db = getWritableDatabase(PASSWORD);
        db.insert(TABLE_NAME, null, values);
    }

    public Cursor query(){
        SQLiteDatabase db = getReadableDatabase(PASSWORD);
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        return c;
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase(PASSWORD);
        db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
    }

    public void update(ContentValues values, String whereClause, String[]whereArgs){
        SQLiteDatabase db = getWritableDatabase(PASSWORD);
        db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }
}
