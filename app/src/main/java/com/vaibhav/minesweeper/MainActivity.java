package com.vaibhav.minesweeper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.ModelMT.MusicTheme;
import com.vaibhav.minesweeper.ParametersLevels.paramsLevel;
import com.vaibhav.minesweeper.ParametersMT.paramsMT;

public class MainActivity extends AppCompatActivity {
    CountDownTimer t;
    boolean timeOver = false;
    public void play(View view) {
        if(timeOver) {
            Intent intent = new Intent(this, minesweeperOrEight.class);
            startActivity(intent);
            finish();
        }
    }
    public void howToPlay(View view) {
        if(timeOver) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
            finish();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exit(View view) {
        if(timeOver) {
            finishAffinity();
            PerfectLoopMediaPlayer.release();
        }
    }
    public void highScores(View view) {
        if(timeOver) {
            Intent intent = new Intent(this, HighScores.class);
            startActivity(intent);
            finish();
        }
    }
    public void theme(View view) {
        if(timeOver) {
            Intent intent = new Intent(this, chooseTheme.class);
            startActivity(intent);
            finish();
        }
    }
    public void doNothing(View view) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView play = (ImageView)findViewById(R.id.playButton);
        final ImageView set = (ImageView)findViewById(R.id.settingsButton);
        final ImageView th = (ImageView)findViewById(R.id.themesButton);
        final ImageView hi = (ImageView)findViewById(R.id.highScoreButton);
        final ImageView exi = (ImageView)findViewById(R.id.exitButton);
        final View view1 = findViewById(R.id.back);
        final View view2 = findViewById(R.id.layoutScreen);
        final MediaPlayer[] ring = new MediaPlayer[1];
        final ImageView img = findViewById(R.id.background);
        img.setImageResource(R.drawable.bomb);
        final TextView textView = findViewById(R.id.txt);
        ring[0] = MediaPlayer.create(MainActivity.this, R.raw.timergone);
        ring[0].start();
        t = new CountDownTimer(2000, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                ring[0].stop();
                ring[0].release();
                play.setImageResource(R.drawable.play);
                hi.setImageResource(R.drawable.trophy);
                set.setImageResource(R.drawable.settings);
                th.setImageResource(R.drawable.themes);
                exi.setImageResource(R.drawable.exit);
                textView.setText("PLAY");
                int deltaX = (view2.getWidth() / 2) - (hi.getWidth() / 2);
                int deltaY = (view2.getHeight() / 2) - (hi.getHeight() / 2);
                Log.d("abc", ""+deltaX);
                TranslateAnimation anim = new TranslateAnimation(
                        TranslateAnimation.ABSOLUTE,+deltaX,
                        TranslateAnimation.ABSOLUTE,0.0f,
                        TranslateAnimation.ABSOLUTE,+deltaY,
                        TranslateAnimation.ABSOLUTE,0.0f
                );
                anim.setFillAfter(true);
                anim.setDuration(400);
                hi.startAnimation(anim);

                anim = new TranslateAnimation(
                        TranslateAnimation.ABSOLUTE,-deltaX,
                        TranslateAnimation.ABSOLUTE,0.0f,
                        TranslateAnimation.ABSOLUTE,+deltaY,
                        TranslateAnimation.ABSOLUTE,0.0f
                );
                anim.setFillAfter(true);
                anim.setDuration(400);
                th.startAnimation(anim);

                anim = new TranslateAnimation(
                        TranslateAnimation.ABSOLUTE,+deltaX,
                        TranslateAnimation.ABSOLUTE,0.0f,
                        TranslateAnimation.ABSOLUTE,-deltaY,
                        TranslateAnimation.ABSOLUTE,0.0f
                );
                anim.setFillAfter(true);
                anim.setDuration(400);
                set.startAnimation(anim);

                anim = new TranslateAnimation(
                        TranslateAnimation.ABSOLUTE,-deltaX,
                        TranslateAnimation.ABSOLUTE,0.0f,
                        TranslateAnimation.ABSOLUTE,-deltaY,
                        TranslateAnimation.ABSOLUTE,0.0f
                );
                anim.setFillAfter(true);
                anim.setDuration(400);
                exi.startAnimation(anim);

                ring[0] = MediaPlayer.create(MainActivity.this, R.raw.ring);
                ring[0].start();
                img.setImageResource(R.drawable.exploded);
                view2.setBackgroundColor(Color.parseColor("#4D2600"));
                //view2.setBackgroundResource(R.drawable.exploded);
                //textView.setText("Indian Mines League");
                timeOver = true;
                DataBaseHandlerMT db =  new DataBaseHandlerMT(MainActivity.this);
                try {
                    if (db.showMusic() == 1) {
                        PerfectLoopMediaPlayer.release();
                        PerfectLoopMediaPlayer.create(MainActivity.this, R.raw.bg);
                    } else if (db.showMusic() == 2) {
                        PerfectLoopMediaPlayer.release();
                        PerfectLoopMediaPlayer.create(MainActivity.this, R.raw.bg2);
                    } else if (db.showMusic() == 3) {
                        PerfectLoopMediaPlayer.release();
                        PerfectLoopMediaPlayer.create(MainActivity.this, R.raw.bg3);
                    } else {
                        PerfectLoopMediaPlayer.release();
                        PerfectLoopMediaPlayer.create(MainActivity.this, R.raw.bg4);
                    }
                } catch(Exception e) {
                    PerfectLoopMediaPlayer.create(MainActivity.this, R.raw.bg);
                    db.addMusic(new MusicTheme(1));
                }

            }

        }.start();
    }
}