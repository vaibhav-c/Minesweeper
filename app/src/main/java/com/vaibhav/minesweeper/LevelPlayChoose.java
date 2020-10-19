package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.vaibhav.minesweeper.HandlerLevels.dataBaseHandlerLevels;
import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.ModelLevels.level;

public class LevelPlayChoose extends AppCompatActivity {
    ImageView l1;
    ImageView l2;
    ImageView l3;
    ImageView l4;
    ImageView l5;
    ImageView l6;
    ImageView l7;
    ImageView l8;
    ImageView l9;
    ImageView l10;
    ImageView l11;
    ImageView l12;
    int whetherEight = 0;
    static final String abc = "com.vaibhav.minesweeper.LevelPlayChoose";
    public void showLevelButton() {
        l1 = findViewById(R.id.level1);
        l2 = findViewById(R.id.level2);
        l3 = findViewById(R.id.level3);
        l4 = findViewById(R.id.level4);
        l5 = findViewById(R.id.level5);
        l6 = findViewById(R.id.level6);
        l7 = findViewById(R.id.level7);
        l8 = findViewById(R.id.level8);
        l9 = findViewById(R.id.level9);
        l10 = findViewById(R.id.level10);
        l11 = findViewById(R.id.level11);
        l12 = findViewById(R.id.level12);
        dataBaseHandlerLevels db = new dataBaseHandlerLevels(LevelPlayChoose.this);
        int n;
        try {
            n = db.showLevelNo();
        } catch(Exception e) {
            db.addLevel(new level(1));
            n = 1;
        }
        l1.setImageResource(R.drawable.levone);
        if(n >= 2) {
            l2.setImageResource(R.drawable.levtwo);
        }
        if(n >= 3) {
            l3.setImageResource(R.drawable.levthree);
        }
        if(n >= 4) {
            l4.setImageResource(R.drawable.levfour);
        }
        if(n >= 5) {
            l5.setImageResource(R.drawable.levfive);
        }
        if(n >= 6) {
            l6.setImageResource(R.drawable.levsix);
        }
        if(n >= 7) {
            l7.setImageResource(R.drawable.levseven);
        }
        if(n >= 8) {
            l8.setImageResource(R.drawable.leveight);
        }
        if(n >= 9) {
            l9.setImageResource(R.drawable.levnine);
        }
        if(n >= 10) {
            l10.setImageResource(R.drawable.levten);
        }
        if(n >= 11) {
            l11.setImageResource(R.drawable.leveleven);
        }
        if(n >= 12) {
            l12.setImageResource(R.drawable.levtwelve);
        }

    }
    public void selectLevel(View view) {
        int n;
        dataBaseHandlerLevels db = new dataBaseHandlerLevels(LevelPlayChoose.this);
        try {
            n = db.showLevelNo();
        } catch (Exception e) {
            db.addLevel(new level(1));
            n = 1;
        }
        int l = Integer.parseInt(view.getTag().toString());
        if (l <= n) {
            allowPlay(l);
        }
    }
    private void allowPlay(int l) {
        if(l == 1) {
            Intent intent = new Intent(this, PlayNow2.class);
            intent.putExtra("bombNo", 5);
            intent.putExtra("timeGiven", 120000);
            intent.putExtra("unlock", 2);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 2) {
            Intent intent = new Intent(this, PlayNow2.class);
            intent.putExtra("bombNo", 7);
            intent.putExtra("timeGiven", 100000);
            intent.putExtra("unlock", 3);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 3) {
            Intent intent = new Intent(this, PlayNow2.class);
            intent.putExtra("bombNo", 9);
            intent.putExtra("timeGiven", 90000);
            intent.putExtra("unlock", 4);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 4) {
            Intent intent = new Intent(this, PlayNow2.class);
            intent.putExtra("bombNo", 10);
            intent.putExtra("timeGiven", 80000);
            intent.putExtra("unlock", 5);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 5) {
            Intent intent = new Intent(this, PlayNow1.class);
            intent.putExtra("bombNo", 10);
            intent.putExtra("timeGiven", 90000);
            intent.putExtra("unlock", 6);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 6) {
            Intent intent = new Intent(this, PlayNow1.class);
            intent.putExtra("bombNo", 12);
            intent.putExtra("timeGiven", 75000);
            intent.putExtra("unlock", 7);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 7) {
            Intent intent = new Intent(this, PlayNow1.class);
            intent.putExtra("bombNo", 14);
            intent.putExtra("timeGiven", 60000);
            intent.putExtra("unlock", 8);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 8) {
            Intent intent = new Intent(this, PlayNow1.class);
            intent.putExtra("bombNo", 15);
            intent.putExtra("timeGiven", 45000);
            intent.putExtra("unlock", 9);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 9) {
            Intent intent = new Intent(this, PlayNow.class);
            intent.putExtra("bombNo", 15);
            intent.putExtra("timeGiven", 60000);
            intent.putExtra("unlock", 10);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 10) {
            Intent intent = new Intent(this, PlayNow.class);
            intent.putExtra("bombNo", 17);
            intent.putExtra("timeGiven", 50000);
            intent.putExtra("unlock", 11);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 11) {
            Intent intent = new Intent(this, PlayNow.class);
            intent.putExtra("bombNo", 19);
            intent.putExtra("timeGiven", 45000);
            intent.putExtra("unlock", 12);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        } else if(l == 12) {
            Intent intent = new Intent(this, PlayNow.class);
            intent.putExtra("bombNo", 20);
            intent.putExtra("timeGiven", 40000);
            intent.putExtra("unlock", 13);
            intent.putExtra("isEight", 1);
            startActivity(intent);
        }
    }
    @Override
    protected void onPause() {
        PerfectLoopMediaPlayer.release();
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        DataBaseHandlerMT db = new DataBaseHandlerMT(LevelPlayChoose.this);
        if (db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(LevelPlayChoose.this, R.raw.bg);
        } else if (db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(LevelPlayChoose.this, R.raw.bg2);
        } else if (db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(LevelPlayChoose.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(LevelPlayChoose.this, R.raw.bg4);
        }
    }
    public void onBackPressed(View view) {
        onBackPressed();
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, levelOrCustom.class);
        intent.putExtra("isEight", whetherEight);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_play_choose);
        Intent intent = getIntent();
        whetherEight = intent.getIntExtra("isEight", 0);
        Log.d("whetherEight", "Level "+whetherEight);
        showLevelButton();
    }
}