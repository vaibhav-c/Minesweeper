package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;

public class minesweeperOrEight extends AppCompatActivity {
    public void mine(View view) {
        Intent intent = new Intent(this, levelOrCustom.class);
        intent.putExtra("isEight", 1);
        startActivity(intent);
    }
    public void eight(View view) {
        Intent intent = new Intent(this, levelOrCustom.class);
        intent.putExtra("isEight", 0);
        startActivity(intent);
    }
    public void ques(View view) {
        /*TextView txt = findViewById(R.id.difference);
        txt.setText("Minesweeper is a classic.\n" +
                "The rules and regulations similar to the original minesweeper where you need to disclose the cells not containing bombs.\n" +
                "Minesweeper discloses the numbered cells one by one but cells containing zero disclose themselves in all directions until they reach a cell which contains a number greater than zero.\n" +
                "RevNex is different.\n" +
                "It discloses adjacent cells in all directions no matter they contain zero or number greater than zero but cells containing mines will not be displayed. ");
        */
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popdifference, null);

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
    public void onBackPressed(View view) {
        onBackPressed();

    }
    @Override
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
        DataBaseHandlerMT db = new DataBaseHandlerMT(minesweeperOrEight.this);
        if (db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(minesweeperOrEight.this, R.raw.bg);
        } else if (db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(minesweeperOrEight.this, R.raw.bg2);
        } else if (db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(minesweeperOrEight.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(minesweeperOrEight.this, R.raw.bg4);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_or_eight);
        final ImageView img  = findViewById(R.id.ship1);
        final ImageView img1 = findViewById(R.id.ship2);
        TextView txt = findViewById(R.id.txt);
        TextView txt1 = findViewById(R.id.txt1);

        img1.animate().translationXBy(500f).setDuration(30000);

        txt1.animate().translationXBy(500f).setDuration(30000);
        img.animate().translationXBy(-400f).setDuration(30000);

        txt.animate().translationXBy(-400f).setDuration(30000);
    }
}