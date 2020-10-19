package com.vaibhav.minesweeper.HandlerMT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vaibhav.minesweeper.ModelMT.MusicTheme;
import com.vaibhav.minesweeper.ThemeModel.MusicThemeT;
import com.vaibhav.minesweeper.ParametersMT.paramsMT;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandlerMT extends SQLiteOpenHelper {
    public DataBaseHandlerMT(Context context) {
        super(context, paramsMT.DB_NAME, null, paramsMT.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + paramsMT.TABLE_NAME + "(" +
                paramsMT.KEY_MUSIC + " INTEGER )";
        String query1 = "CREATE TABLE " + paramsMT.TABLE_NAME_THEME + "(" +
                paramsMT.KEY_THEME + " INTEGER )";
        Log.d("Msg", "Query run is" + query);
        Log.d("Msg", "Query run is" + query1);
        db.execSQL(query);
        db.execSQL(query1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addMusic(MusicTheme mus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(String.valueOf(paramsMT.KEY_MUSIC), mus.getMusic());
        db.insert(paramsMT.TABLE_NAME, null, value);
        Log.d("Msg", "Inserted");
        db.close();
    }
    public int showMusic() {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("Msg", "Show music called");
        List<Integer> list = new ArrayList<>();
        String query = "SELECT " + paramsMT.KEY_MUSIC + " FROM " + paramsMT.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("Msg", query);
        Log.d("Msg", "added to list");
        if(cursor.moveToFirst()) {
            do{
                MusicTheme m = new MusicTheme();
                m.setMusic(cursor.getInt(0));
                Log.d("Msg", "added to list");
                list.add(m.getMusic());
            } while(cursor.moveToNext());
        }
        db.close();
        return list.get(list.size() - 1);
    }
    public void addTheme(MusicThemeT mus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(String.valueOf(paramsMT.KEY_THEME), mus.getTheme());
        db.insert(paramsMT.TABLE_NAME_THEME, null, value);
        Log.d("Msg", "Inserted Theme");
        db.close();
    }
    public int showTheme() {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("Msg", "Show theme called");
        List<Integer> list = new ArrayList<>();
        String query = "SELECT " + paramsMT.KEY_THEME + " FROM " + paramsMT.TABLE_NAME_THEME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("Msg", query);
        Log.d("Msg", "added to list");
        if(cursor.moveToFirst()) {
            do{
                MusicThemeT t = new MusicThemeT();
                t.setTheme(cursor.getInt(0));
                Log.d("Msg", "theme added to list");
                list.add(t.getTheme());
            } while(cursor.moveToNext());
        }
        db.close();
        return list.get(list.size() - 1);
    }

}
