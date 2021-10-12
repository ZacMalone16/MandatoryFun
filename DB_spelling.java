package com.example.mandatoryfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_spelling extends SQLiteOpenHelper{
    public DB_spelling(Context context) {
        super(context, "Spelling.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table userInput(userAnswer TEXT primary key /*, date INTEGER, numCorrect INTEGER, numMissed INTEGER*/)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists userInput");
    }

    public Boolean insertuseranswer(String userAnswer /*, Integer date, Integer numCorrect, Integer numMissed*/ )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userAnswer", userAnswer);
       /* contentValues.put("date", date);
        contentValues.put("numCorrect", numCorrect);
        contentValues.put("numMissed", numMissed);*/
        long result=DB.insert("userInput", null, contentValues);
        if(result==-1) {
            return false;
        }
        else{
            return true;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from userInput ", null);
        return cursor;
    }
}
