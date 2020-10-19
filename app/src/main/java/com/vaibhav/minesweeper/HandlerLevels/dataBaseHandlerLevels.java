package com.vaibhav.minesweeper.HandlerLevels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.vaibhav.minesweeper.ModelLevels.level;
import com.vaibhav.minesweeper.ParametersLevels.paramsLevel;

import java.util.ArrayList;
import java.util.List;

public class dataBaseHandlerLevels extends SQLiteOpenHelper {
    public dataBaseHandlerLevels(Context context) {
        super(context, paramsLevel.DB_NAME, null, paramsLevel.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + paramsLevel.TABLE_NAME + "(" +
                paramsLevel.KEY_LEVEL + " INTEGER)";
        String query1 = "CREATE TABLE " + paramsLevel.TABLE_NAME_EIGHT + "(" +
                paramsLevel.KEY_LEVEL_EIGHT + " INTEGER)";
        Log.d("Msg", "Query run is" + query);
        Log.d("Msg", "Query run is" + query1);
        db.execSQL(query);
        db.execSQL(query1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addLevel(level lev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(String.valueOf(paramsLevel.KEY_LEVEL), lev.getL());
        db.insert(paramsLevel.TABLE_NAME, null, value);
        Log.d("Msg", "Inserted");
        db.close();
    }
    public int showLevelNo() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Integer> list = new ArrayList<>();
        String query = "SELECT " + paramsLevel.KEY_LEVEL + " FROM " + paramsLevel.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("Msg", query);
        Log.d("Msg", "added to list");
        if(cursor.moveToFirst()) {
            do{
                level c = new level();
                c.setL(cursor.getInt(0));
                Log.d("Msg", "added to list");
                list.add(c.getL());
            } while(cursor.moveToNext());
        }
        db.close();
        return list.get(list.size() - 1);
    }
    public void addLevelEight(level lev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(String.valueOf(paramsLevel.KEY_LEVEL_EIGHT), lev.getL());
        db.insert(paramsLevel.TABLE_NAME_EIGHT, null, value);
        Log.d("Msg", "Inserted");
        db.close();
    }
    public int showLevelNoEight() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Integer> list = new ArrayList<>();
        String query = "SELECT " + paramsLevel.KEY_LEVEL_EIGHT + " FROM " + paramsLevel.TABLE_NAME_EIGHT;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("Msg", query);
        Log.d("Msg", "added to list");
        if(cursor.moveToFirst()) {
            do{
                level c = new level();
                c.setL(cursor.getInt(0));
                Log.d("Msg", "added to list");
                list.add(c.getL());
            } while(cursor.moveToNext());
        }
        db.close();
        return list.get(list.size() - 1);
    }
}
