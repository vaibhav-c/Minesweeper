package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class chooseLevel extends AppCompatActivity {
    public void easy(View view) {
        Intent intent = new Intent(this, PlayNow2.class);
        startActivity(intent);
    }
    public void medium(View view) {
        Intent intent = new Intent(this, PlayNow1.class);
        startActivity(intent);
    }
    public void hard(View view) {
        Intent intent = new Intent(this, PlayNow.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
    }
}