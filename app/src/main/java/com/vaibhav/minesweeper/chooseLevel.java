package com.vaibhav.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.vaibhav.minesweeper.Parameters.params;

public class chooseLevel extends AppCompatActivity {
    int whetherEight = 0;
    public void ques(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popcustom, null);

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
    public void easy(View view) {
        int bombs = 10, time = 120000;
        try {
            EditText bom = (EditText) findViewById(R.id.enterBombNo);
            bombs = Integer.parseInt(bom.getText().toString());
            EditText tim = (EditText) findViewById(R.id.enterTime);
            time = Integer.parseInt(tim.getText().toString());
            if (bombs <= 1 || bombs > 50) {
                Toast.makeText(this, "Enter valid bomb number", Toast.LENGTH_SHORT).show();
            } else if (time <= 0 || time > 600) {
                Toast.makeText(this, "Enter valid time", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, PlayNowCustom2.class);
                intent.putExtra("bombNo", bombs);
                intent.putExtra("timeGiven", time * 1000);
                if (whetherEight == 0) {
                    intent.putExtra("isEight", 0);
                } else {
                    intent.putExtra("isEight", 1);
                }
                startActivity(intent);
                finish();
            }
        } catch(Exception e) {
            Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show();
        }
    }
    public void medium(View view) {
        int bombs = 10, time = 120000;
        try {
            EditText bom = (EditText) findViewById(R.id.enterBombNo);
            bombs = Integer.parseInt(bom.getText().toString());
            EditText tim = (EditText) findViewById(R.id.enterTime);
            time = Integer.parseInt(tim.getText().toString());
            if (bombs <= 1 || bombs > 50) {
                Toast.makeText(this, "Enter valid bomb number", Toast.LENGTH_SHORT).show();
            } else if (time <= 0 || time > 600) {
                Toast.makeText(this, "Enter valid time", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, PlayNowCustom1.class);
                intent.putExtra("bombNo", bombs);
                intent.putExtra("timeGiven", time * 1000);
                if (whetherEight == 0) {
                    intent.putExtra("isEight", 0);
                } else {
                    intent.putExtra("isEight", 1);
                }
                startActivity(intent);
                finish();
            }
        } catch(Exception e) {
            Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show();
        }
    }
    public void hard(View view) {
        int bombs = 10, time = 120000;
        try {
            EditText bom = (EditText) findViewById(R.id.enterBombNo);
            bombs = Integer.parseInt(bom.getText().toString());
            EditText tim = (EditText) findViewById(R.id.enterTime);
            time = Integer.parseInt(tim.getText().toString());
            if (bombs <= 1 || bombs > 50) {
                Toast.makeText(this, "Enter valid bomb number", Toast.LENGTH_SHORT).show();
            } else if (time <= 0 || time > 600) {
                Toast.makeText(this, "Enter valid time", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, PlayNowCustom.class);
                intent.putExtra("bombNo", bombs);
                intent.putExtra("timeGiven", time * 1000);
                if (whetherEight == 0) {
                    intent.putExtra("isEight", 0);
                } else {
                    intent.putExtra("isEight", 1);
                }
                startActivity(intent);
                finish();
            }
        } catch(Exception e) {
            Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed(View view) {
        Intent intent = new Intent(this, levelOrCustom.class);
        intent.putExtra("isEight", whetherEight);
        startActivity(intent);
        finish();
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
        setContentView(R.layout.activity_choose_level);
        Intent intent = getIntent();
        whetherEight = intent.getIntExtra("isEight", 0);
        Log.d("whetherEight", "LorCustom"+whetherEight);
    }
}