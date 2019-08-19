package com.example.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onQuery(String sql){
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor ongetData(String sql){
        SQLiteDatabase sqLiteDatabase  = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
