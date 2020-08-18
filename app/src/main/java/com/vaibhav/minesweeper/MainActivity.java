package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public void play(View view) {
        Intent intent = new Intent(this, chooseLevel.class);
        startActivity(intent);
    }
    public void howToPlay(View view) {
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
    public void exit(View view) {
        finish();
        System.exit(0);
    }
    public void doNothing(View view) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer[] ring = new MediaPlayer[1];
        final ImageView img = findViewById(R.id.background);
        img.setImageResource(R.drawable.bomb);
        new CountDownTimer(1000, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                ring[0] = MediaPlayer.create(MainActivity.this, R.raw.ring);
                ring[0].start();
                img.setImageResource(R.drawable.exploded);
            }
        }.start();
    }
}