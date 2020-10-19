package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vaibhav.minesweeper.Handler.dataBaseHandler;
import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.Model.highScore;
import com.vaibhav.minesweeper.Parameters.params;

import java.util.ArrayList;
import java.util.List;

public class HighScores extends AppCompatActivity {
    ListView listView;
    TextView t;
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        int m = 1;
        t = findViewById(R.id.exp);
        t.setText("Rank  Date  &  Time  Score");
        try {
            ArrayList<String> highscore = new ArrayList<>();
            listView = findViewById(R.id.highScores);
            dataBaseHandler db = new dataBaseHandler(HighScores.this);
            List<highScore> hsList = db.showHighScore();
            for (highScore hs : hsList) {
                if(m < 10) {
                    Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
                    highscore.add("     " + m + "            " + hs.getDate() + "                 " + hs.getScore());
                    m++;
                } else if(m < 100){
                    Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
                    highscore.add("     " + m + "          " + hs.getDate() + "                 " + hs.getScore());
                    m++;
                } else if(m < 1000) {
                    Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
                    highscore.add("     " + m + "         " + hs.getDate() + "                 " + hs.getScore());
                    m++;
                } else {
                    Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
                    highscore.add("     " + m + "        " + hs.getDate() + "                 " + hs.getScore());
                    m++;
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highscore);
            listView.setAdapter(arrayAdapter);
            db.close();
        } catch(Exception e) {

        }
    }
    public void clear(View view) {
        this.deleteDatabase(params.DB_NAME);
        listView.setAdapter(null);
    }
    public void show(View view) {
        TextView show = findViewById(R.id.showof);
        if(c % 2 == 0) {
            forEight();
            show.setText("RevNex");
        } else {
            forNotEight();
            show.setText("Minesweeper");
        }
        c++;
    }
    protected void onPause() {
        PerfectLoopMediaPlayer.release();
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        DataBaseHandlerMT db = new DataBaseHandlerMT(HighScores.this);
        if (db.showMusic() == 1) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(HighScores.this, R.raw.bg);
        } else if (db.showMusic() == 2) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(HighScores.this, R.raw.bg2);
        } else if (db.showMusic() == 3) {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(HighScores.this, R.raw.bg3);
        } else {
            PerfectLoopMediaPlayer.release();
            PerfectLoopMediaPlayer.create(HighScores.this, R.raw.bg4);
        }
    }
    private void forEight() {
        int m = 1;
        t = findViewById(R.id.exp);
        t.setText("Rank  Date  &  Time  Score");
        listView.setAdapter(null);
        try {
            ArrayList<String> highscoreEight = new ArrayList<>();
            listView = findViewById(R.id.highScores);
            dataBaseHandler db = new dataBaseHandler(HighScores.this);
            List<highScore> hsList = db.showHighScoreEight();
            for (highScore hs : hsList) {
                Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
                highscoreEight.add("     " + m + "            " + hs.getDate() + "                 " + hs.getScore());
                m++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highscoreEight);
            listView.setAdapter(arrayAdapter);
            db.close();
        } catch(Exception e) {

        }
    }
    private void forNotEight() {
        int m = 1;
        t = findViewById(R.id.exp);
        t.setText("Rank  Date  &  Time  Score");
        listView.setAdapter(null);
        try {
            ArrayList<String> highscore = new ArrayList<>();
            listView = findViewById(R.id.highScores);
            dataBaseHandler db = new dataBaseHandler(HighScores.this);
            List<highScore> hsList = db.showHighScore();
            for (highScore hs : hsList) {
                Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
                highscore.add("     " + m + "            " + hs.getDate() + "                 " + hs.getScore());
                m++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highscore);
            listView.setAdapter(arrayAdapter);
            db.close();
        } catch(Exception e) {

        }
    }
    public void onBackPressed(View view) {
        onBackPressed();
    }
    public void onBackPressed() {
        PerfectLoopMediaPlayer.release();
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }
}