package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.ModelMT.MusicTheme;
import com.vaibhav.minesweeper.ParametersLevels.paramsLevel;
import com.vaibhav.minesweeper.ParametersMT.paramsMT;

public class settings extends AppCompatActivity {
    AudioManager audioManager;
    public void optionA(View v) {
        DataBaseHandlerMT db = new DataBaseHandlerMT(settings.this);
        db.addMusic(new MusicTheme(1));
        changeMusic();
    }
    public void optionB(View v) {
        DataBaseHandlerMT db = new DataBaseHandlerMT(settings.this);
        db.addMusic(new MusicTheme(2));
        changeMusic();
    }
    public void optionC(View v) {
        DataBaseHandlerMT db = new DataBaseHandlerMT(settings.this);
        db.addMusic(new MusicTheme(3));
        changeMusic();
    }
    public void optionD(View v) {
        DataBaseHandlerMT db = new DataBaseHandlerMT(settings.this);
        db.addMusic(new MusicTheme(4));
        changeMusic();
    }
    public void credits(View v) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
    private void changeMusic() {
        DataBaseHandlerMT db =  new DataBaseHandlerMT(settings.this);
        try {
            if (db.showMusic() == 1) {
                PerfectLoopMediaPlayer.release();
                PerfectLoopMediaPlayer.create(settings.this, R.raw.bg);
            } else if (db.showMusic() == 2) {
                PerfectLoopMediaPlayer.release();
                PerfectLoopMediaPlayer.create(settings.this, R.raw.bg2);
            } else if (db.showMusic() == 3) {
                PerfectLoopMediaPlayer.release();
                PerfectLoopMediaPlayer.create(settings.this, R.raw.bg3);
            } else {
                PerfectLoopMediaPlayer.release();
                PerfectLoopMediaPlayer.create(settings.this, R.raw.bg4);
            }
        } catch(Exception e) {
            PerfectLoopMediaPlayer.create(settings.this, R.raw.bg);
            db.addMusic(new MusicTheme(1));
        }

    }
    public void Reset(View v) {
        this.deleteDatabase(paramsLevel.DB_NAME);
    }
    public void back(View v) {
        onBackPressed();
    }
    public void onBackPressed() {
        PerfectLoopMediaPlayer.release();
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        PerfectLoopMediaPlayer.release();
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        DataBaseHandlerMT db = new DataBaseHandlerMT(settings.this);
        if (db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(settings.this, R.raw.bg);
        } else if (db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(settings.this, R.raw.bg2);
        } else if (db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(settings.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(settings.this, R.raw.bg4);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        SeekBar seekVol = findViewById(R.id.seekBar3);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}