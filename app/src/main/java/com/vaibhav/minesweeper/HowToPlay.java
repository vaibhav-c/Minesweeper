package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HowToPlay extends AppCompatActivity {
    ListView list;
    public void play(View view) {
        Intent intent = new Intent(this, chooseLevel.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        final TextView text = findViewById(R.id.txt);
        //text.setText(" ");
        ArrayList<String> arr = new ArrayList<>();
        list = findViewById(R.id.describe);
        arr.add("Levels");
        arr.add("How to Play?");
        arr.add("Scoring Pattern");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arr);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(((TextView) view).getText().toString().equals("Levels")) {
                    text.setText("There are three levels easy, medium and hard.\nEASY - 8 x 8 board with 10 bombs TIME : 120s\nMEDIUM - 9 x 9 board with 15 bombs TIME: 90s\nHARD - 10 x 10 board with 20 bombs TIME : 60s");
                } else if(((TextView) view).getText().toString().equals("How to Play?")) {
                    text.setText("1. Touch a cell to reveal it. The timer will start after your first touch on the board.\n" +
                            "2. Mark and un-mark buttons will help to mark/un-mark a cell. The marked cell will not reveal them unless unmarked or all cells without bombs are unveiled.\n" +
                            "3. The mark button will mark only the cell you click after clicking the mark button. For marking two cells, press the mark button then mark a cell and repeat the process. The un-mark button will work similarly.\n" +
                            "4. The reset button will help you reset the game at any time and timer of the new game will begin automatically The game allows you to play beyond the time limit.");
                } else if(((TextView) view).getText().toString().equals("Scoring Pattern")) {
                    text.setText("High Score will be set according to time remaining(T):\n4 x T in HARD\n2 x T in MEDIUM\nT in EASY.");
                }
            }
        });
    }
}