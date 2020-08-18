package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HowToPlay extends AppCompatActivity {
    public void play(View view) {
        Intent intent = new Intent(this, chooseLevel.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        TextView text = findViewById(R.id.textView3);
        text.setText("There are three levels easy, medium and hard.\n\nEASY - 8 x 8 board with 10 bombs TIME : 120s\nMEDIUM - 9 x 9 board with 15 bombs TIME: 90s\nHARD - 10 x 10 board with 20 bombs TIME : 60s\n\n          Touch a cell to reveal it. The mark and un-mark buttons given below will help to mark/un-mark a cell as per your requirement. The marked cell will not reveal them unless unmarked or you have unveiled all cells without bombs. The un-mark option will help you un-mark the cells. The marked option will mark only the cell you click after clicking the mark button if you wish to mark two cells press the mark button then mark a cell and repeat the process. The un-mark button will work similarly. The reset button will help you reset the game at any time. The timer will begin after your first click but on resetting the game the timer will begin automatically from the moment you reset the game. The game allows you to play beyond the time limit.");
    }
}