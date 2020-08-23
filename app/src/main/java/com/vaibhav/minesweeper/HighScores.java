package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vaibhav.minesweeper.Handler.dataBaseHandler;
import com.vaibhav.minesweeper.Model.highScore;
import com.vaibhav.minesweeper.Parameters.params;

import java.util.ArrayList;
import java.util.List;

public class HighScores extends AppCompatActivity {
    ListView listView;
    TextView t;
    int m = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        t = findViewById(R.id.exp);
        t.setText("Date & Time                          Score");
        ArrayList<String> highscore = new ArrayList<>();
        listView = findViewById(R.id.highScores);
        dataBaseHandler db = new dataBaseHandler(HighScores.this);
        List<highScore> hsList = db.showHighScore();
        for(highScore hs : hsList) {
            Log.d("Msg", "Data: ID:" + hs.getId() + " Date:" + hs.getDate() + " Score:" + hs.getScore());
            highscore.add(m + "        " + hs.getDate() + "                " + hs.getScore());
            m++;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highscore);
        listView.setAdapter(arrayAdapter);
        db.close();
    }
    public void clear(View view) {
        this.deleteDatabase(params.DB_NAME);
        listView.setAdapter(null);
    }
}