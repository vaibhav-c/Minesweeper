package com.vaibhav.minesweeper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.ModelMT.MusicTheme;
import com.vaibhav.minesweeper.ParametersLevels.paramsLevel;
import com.vaibhav.minesweeper.ParametersMT.paramsMT;

public class MainActivity1 extends AppCompatActivity {
    public void play(View view) {
        Intent intent = new Intent(this, minesweeperOrEight.class);
        startActivity(intent);
    }
    public void howToPlay(View view) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exit(View view) {
        finishAffinity();
        PerfectLoopMediaPlayer.release();
    }
    public void highScores(View view) {
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
    }
    public void theme(View view) {
        Intent intent = new Intent(this, chooseTheme.class);
        startActivity(intent);
    }
    public void doNothing(View view) {

    }
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ImageView play = (ImageView)findViewById(R.id.playButton);
        ImageView set = (ImageView)findViewById(R.id.settingsButton);
        ImageView th = (ImageView)findViewById(R.id.themesButton);
        ImageView hi = (ImageView)findViewById(R.id.highScoreButton);
        ImageView exi = (ImageView)findViewById(R.id.exitButton);
        play.setImageResource(R.drawable.play);
        hi.setImageResource(R.drawable.trophy);
        set.setImageResource(R.drawable.settings);
        th.setImageResource(R.drawable.themes);
        exi.setImageResource(R.drawable.exit);
        DataBaseHandlerMT db =  new DataBaseHandlerMT(MainActivity1.this);
        if(db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(MainActivity1.this, R.raw.bg);
        } else if(db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(MainActivity1.this, R.raw.bg2);
        } else if(db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(MainActivity1.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(MainActivity1.this, R.raw.bg4);
        }
    }
}