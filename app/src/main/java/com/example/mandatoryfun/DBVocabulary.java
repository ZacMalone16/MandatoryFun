package com.example.mandatoryfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBVocabulary extends SQLiteOpenHelper{
    public DBVocabulary(Context context) {
        super(context, "Vocabulary.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DBv) {
        DBv.execSQL("create Table userInput(vocabAnswer TEXT primary key , vocabDefin TEXT /*, numCorrect INTEGER, numMissed INTEGER*/)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DBv, int i, int i1) {
        DBv.execSQL("drop Table if exists userInput");
    }

    public Boolean insertuseranswer(String vocabAnswer  /*, Integer numCorrect, Integer numMissed*/)
    {
        SQLiteDatabase DBv = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vocabAnswer", vocabAnswer);
        //contentValues.put("vocabDefin", vocabDefin);
        /*contentValues.put("date", date);
        contentValues.put("counter", counter);*/
        long result=DBv.insert("userInput", null, contentValues);
        if(result==-1) {
            return false;
        }
        else{
            return true;
        }

    }


    public Boolean updateuserdata(String vocabAnswer) {
        SQLiteDatabase DBv = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("vocabDefin", vocabDefin);
        //contentValues.put("dob", dob);
        Cursor cursor = DBv.rawQuery("Select * from userInput where vocabAnswer = ?", new String[]{vocabAnswer});
        if (cursor.getCount() > 0) {
            long result = DBv.update("userInput", contentValues, "vocabAnswer=?", new String[]{vocabAnswer});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String vocabAnswer)
    {
        SQLiteDatabase DBv = this.getWritableDatabase();
        Cursor cursor = DBv.rawQuery("Select * from userInput where vocabAnswer = ?", new String[]{vocabAnswer});
        if (cursor.getCount() > 0) {
            long result = DBv.delete("userInput", "vocabAnswer=?", new String[]{vocabAnswer});
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
        SQLiteDatabase DBv = this.getWritableDatabase();
        Cursor cursor = DBv.rawQuery("Select * from userInput ", null);
        return cursor;
    }
}