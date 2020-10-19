package com.vaibhav.minesweeper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.Parameters.params;
import com.vaibhav.minesweeper.ParametersLevels.paramsLevel;

public class levelOrCustom extends AppCompatActivity {
    int whetherEight = 0;
    ImageView img, img1;
    View view;

    public void levelPlay(View v) {
        if (whetherEight == 0) {
            Intent intent = new Intent(this, LevelPlayChooseEight.class);
            intent.putExtra("isEight", whetherEight);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LevelPlayChoose.class);
            intent.putExtra("isEight", whetherEight);
            startActivity(intent);
            finish();
        }
    }

    public void customPlay(View v) {
        Intent intent = new Intent(this, chooseLevel.class);
        intent.putExtra("isEight", whetherEight);
        startActivity(intent);
        finish();
    }

    public void ques(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popinstructions, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }
    @Override
    protected void onPause() {
        PerfectLoopMediaPlayer.release();
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        DataBaseHandlerMT db = new DataBaseHandlerMT(levelOrCustom.this);
        if (db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(levelOrCustom.this, R.raw.bg);
        } else if (db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(levelOrCustom.this, R.raw.bg2);
        } else if (db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(levelOrCustom.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(levelOrCustom.this, R.raw.bg4);
        }
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, minesweeperOrEight.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_or_custom);
        Intent intent = getIntent();
        whetherEight = intent.getIntExtra("isEight", 0);
        Log.d("whetherEight", "LevelorCustom"+whetherEight);
    }
}