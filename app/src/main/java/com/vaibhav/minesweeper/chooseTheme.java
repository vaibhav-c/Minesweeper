package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.ThemeModel.MusicThemeT;

public class chooseTheme extends AppCompatActivity {
    String s;
    TextView txt;
    public void setheme(View view) {
        s = String.format("" + view.getTag());
        DataBaseHandlerMT db = new DataBaseHandlerMT(chooseTheme.this);
        if(s.equals("mumbai")) {
            db.addTheme(new MusicThemeT(1));
        } else if(s.equals("chennai")){
            db.addTheme(new MusicThemeT(2));
        } else if(s.equals("kolkata")){
            db.addTheme(new MusicThemeT(3));
        } else if(s.equals("hyderabad")){
            db.addTheme(new MusicThemeT(4));
        } else if(s.equals("rajasthan")){
            db.addTheme(new MusicThemeT(5));
        } else if(s.equals("bangalore")){
            db.addTheme(new MusicThemeT(6));
        } else if(s.equals("punjab")){
            db.addTheme(new MusicThemeT(7));
        } else if(s.equals("delhi")) {
            db.addTheme(new MusicThemeT(8));
        } else {
            db.addTheme(new MusicThemeT(9));
        }
        Log.d("Theme",s);
        txt.setText(s + " selected");
    }
    public void onBackPressed(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        PerfectLoopMediaPlayer.release();
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }
    protected void onPause() {
        PerfectLoopMediaPlayer.release();
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        DataBaseHandlerMT db = new DataBaseHandlerMT(chooseTheme.this);
        if (db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(chooseTheme.this, R.raw.bg);
        } else if (db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(chooseTheme.this, R.raw.bg2);
        } else if (db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(chooseTheme.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(chooseTheme.this, R.raw.bg4);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_theme);
        txt = findViewById(R.id.textView);
    }
}