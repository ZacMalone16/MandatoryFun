package com.example.mandatoryfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SpellingList.db";
    public static final String TABLE_NAME1 = "SpellingTable";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "WORD";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "DEF";
    public static final String COL_4 = "STARS";
    public static final String COL_5 = "CHOICE_STARS";
    public static final String TABLE_NAME2 = "VoabTable";
    public static final String VOL_0 = "ID";
    public static final String VOL_1 = "WORD";
    public static final String VOL_2 = "DATE";
    public static final String VOL_3 = "DEF";
    public static final String VOL_4 = "STARS";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT, DATE TEXT, DEF TEXT, STARS INTERGER, CHOICE_STARS INTERGER)" );
        db.execSQL("create table " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT, DATE TEXT, DEF TEXT, STARS INTERGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);

    }
    public boolean insertSpellingData(String word, String date, String def, Integer stars, Integer choice_stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, word);
        cv.put(COL_2, date);
        cv.put(COL_3, def);
        cv.put(COL_4, stars);
        cv.put(COL_5, choice_stars);
        long results = db.insert(TABLE_NAME1, null, cv);

        if(results == -1)
            return false;
        else
            return true;
    }
    public boolean insertVocabData(String word, String date, String def, Integer stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VOL_1, word);
        cv.put(VOL_2, date);
        cv.put(VOL_3, def);
        cv.put(VOL_4, stars);
        long results = db.insert(TABLE_NAME2,null, cv);

        if(results == -1)
            return false;
        else
            return true;
    }
    public Integer spellCount(){
        Integer count = 0;

        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME1;
        //run
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.getCount();
        cursor.moveToLast();
        count = cursor.getInt(0);
        cursor.close();

        return count;
    }

    public Integer vocabCount(){
        Integer count = 0;

        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME2;
        //run
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.getCount();
        cursor.moveToLast();
        count = cursor.getInt(0);
        cursor.close();

        return count;
    }
    public Integer spellStar(Integer level) {
        Integer results = 0;
        String sql = "SELECT * FROM " + TABLE_NAME1;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToPosition(level);
        results = cursor.getInt(4);
        return results;
    }
    public Integer choiceStar(Integer level) {
        Integer results = 0;
        String sql = "SELECT * FROM " + TABLE_NAME1;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToPosition(level);
        results = cursor.getInt(5);
        return results;
    }
    public Integer vocabStar(Integer level) {
        Integer results = 0;
        String sql = "SELECT * FROM " + TABLE_NAME2;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToPosition(level);
        results = cursor.getInt(4);
        return results;
    }
    public String getWord(Integer level) {
        String results;
        String sql = "SELECT * FROM " + TABLE_NAME1 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results = cursor.getString(1);
        return results;
    }
    public String getDef(Integer level) {
        String results;
        String sql = "SELECT * FROM " + TABLE_NAME1 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results = cursor.getString(3);
        return results;
    }
    public String getVocabWord(Integer level) {
        String results;
        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results = cursor.getString(1);
        return results;
    }
    public String getVocabDef(Integer level) {
        String results;
        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results = cursor.getString(3);
        return results;
    }

    public void updateSpellStar(Integer level) {
        Integer results0;
        String results1;
        String results2;
        String results3;
        Integer results4;
        Integer results5;
        Integer results6;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String sql = "SELECT * FROM " + TABLE_NAME1 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results0 = cursor.getInt(0);

        cursor.moveToFirst();
        results1 = cursor.getString(1);

        cursor.moveToFirst();
        results2 = cursor.getString(2);

        cursor.moveToFirst();
        results3 = cursor.getString(3);

        cursor.moveToFirst();
        results4 = cursor.getInt(4);

        cursor.moveToFirst();
        results5 = cursor.getInt(5);

        cv.put(COL_1, results1);
        cv.put(COL_2, results2);
        cv.put(COL_3, results3);
        cv.put(COL_4, (results4 + 1));
        cv.put(COL_5, results5);

        db.update(TABLE_NAME1, cv, "ID = ?", new String[] {"" + results0});
    }
    public void updateSpellStar2(Integer level) {
        Integer results0;
        String results1;
        String results2;
        String results3;
        Integer results4;
        Integer results5;
        Integer results6;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String sql = "SELECT * FROM " + TABLE_NAME1 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results0 = cursor.getInt(0);

        cursor.moveToFirst();
        results1 = cursor.getString(1);

        cursor.moveToFirst();
        results2 = cursor.getString(2);

        cursor.moveToFirst();
        results3 = cursor.getString(3);

        cursor.moveToFirst();
        results4 = cursor.getInt(4);

        cursor.moveToFirst();
        results5 = cursor.getInt(5);

        cv.put(COL_1, results1);
        cv.put(COL_2, results2);
        cv.put(COL_3, results3);
        cv.put(COL_4, results4);
        cv.put(COL_5, (results5 + 1));

        db.update(TABLE_NAME1, cv, "ID = ?", new String[] {"" + results0});
    }

    public void updateVocabStar(Integer level) {
        Integer results0;
        String results1;
        String results2;
        String results3;
        Integer results4;


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE ID=" + level + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        results0 = cursor.getInt(0);

        cursor.moveToFirst();
        results1 = cursor.getString(1);

        cursor.moveToFirst();
        results2 = cursor.getString(2);

        cursor.moveToFirst();
        results3 = cursor.getString(3);

        cursor.moveToFirst();
        results4 = cursor.getInt(4);

        cv.put(VOL_1, results1);
        cv.put(VOL_2, results2);
        cv.put(VOL_3, results3);
        cv.put(VOL_4, (results4 + 1));


        db.update(TABLE_NAME2, cv, "ID = ?", new String[] {"" + results0});
    }
    public boolean deleteSpelling(String word)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "ID + = ?", new String[]{word}) > 0;
    }
    public boolean deleteVocab(String word)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "ID + = ?", new String[]{word}) > 0;
    }
}
