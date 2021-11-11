package com.example.mandatoryfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBSpelling extends SQLiteOpenHelper{
    public DBSpelling(Context context) {
        super(context, "Spelling.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table userInput(spellAnswer TEXT primary key /*, spellDefin TEXT, date INTEGER, counter INTEGER*/)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists userInput");
    }

    public Boolean insertuseranswer(String spellAnswer /*, String spellDefin, Integer date, Integer counter*/ )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("spellAnswer", spellAnswer);
       /* contentValues.put("spellDefin", spellDefin);
        contentValues.put("date", date);
        contentValues.put("counter", counter);*/
        long result=DB.insert("userInput", null, contentValues);
        if(result==-1) {
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean updateuserdata(String spellAnswer) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("spellDefin", spellDefin);
        //contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from userInput where spellAnswer = ?", new String[]{spellAnswer});
        if (cursor.getCount() > 0) {
            long result = DB.update("userInput", contentValues, "spellAnswer=?", new String[]{spellAnswer});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String spellAnswer)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from userInput where spellAnswer = ?", new String[]{spellAnswer});
        if (cursor.getCount() > 0) {
            long result = DB.delete("userInput", "spellAnswer=?", new String[]{spellAnswer});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from userInput ", null);
        return cursor;
    }
}