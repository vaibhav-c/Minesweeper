package com.vaibhav.minesweeper.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vaibhav.minesweeper.Model.highScore;
import com.vaibhav.minesweeper.Parameters.params;

import java.util.ArrayList;
import java.util.List;

public class dataBaseHandler extends SQLiteOpenHelper {
    public dataBaseHandler(Context context) {
        super(context, params.DB_NAME, null, params.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + params.TABLE_NAME + "("
                + params.KEY_ID + " INTEGER PRIMARY KEY, " + params.KEY_SCORE +
                " INTEGER,"  + params.KEY_DATE + " TEXT)";
        Log.d("Msg", "Query run is" + query);
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addHighScore(highScore hs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(params.KEY_SCORE, hs.getScore());
        value.put(params.KEY_DATE, hs.getDate());
        db.insert(params.TABLE_NAME, null, value);
        Log.d("Msg", "Inserted");
        db.close();
    }
    public List<highScore> showHighScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<highScore> list = new ArrayList<>();
        String query = "SELECT * FROM " + params.TABLE_NAME + " ORDER BY " + params.KEY_SCORE + " DESC ";
        Cursor cursor = db.rawQuery(query, null);
        Log.d("Msg", query);
        Log.d("Msg", "added to list");
        if(cursor.moveToFirst()) {
            do{
                highScore c = new highScore();
                c.setId(Integer.parseInt(cursor.getString(0)));
                c.setScore(cursor.getLong(1));
                c.setDate(cursor.getString(2));
                Log.d("Msg", "added to list");
                list.add(c);
            } while(cursor.moveToNext());
        }
        db.close();
        return list;
    }
}
