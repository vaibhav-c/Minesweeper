package com.vaibhav.minesweeper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vaibhav.minesweeper.Handler.dataBaseHandler;
import com.vaibhav.minesweeper.HandlerLevels.dataBaseHandlerLevels;
import com.vaibhav.minesweeper.HandlerMT.DataBaseHandlerMT;
import com.vaibhav.minesweeper.Model.highScore;
import com.vaibhav.minesweeper.ModelLevels.level;
import com.vaibhav.minesweeper.ThemeModel.MusicThemeT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PlayNow extends AppCompatActivity{
    int c = 0, timeOver = 0, tt = 0, lost = 0, resEt = 0, bombNo = 20, timerValue = 60000, unlockLevel = 0;
    boolean marked = false, unmarked = false;
    TextView txt;
    int whetherEight = 0;
    public MediaPlayer ring;
    char[][] arr = new char[10][10];//display
    int[][] arrData = new int[10][10];//data
    int[][] unique = new int[bombNo][2];//Change for new number of bombs
    int[] a = new int[bombNo];//Change for new number of bombs
    int[] b = new int[bombNo];//Change for new number of bombs
    MediaPlayer fireworks;
    MediaPlayer ringer;
    MediaPlayer timerGone;
    int opened = 0;
    CountDownTimer t;
    long tim;
    ImageView imgGrid;
    View viewScreen;
    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cell(View view) {
        ImageView img = (ImageView) view;
        int n = Integer.parseInt(img.getTag().toString());//start tag by 0
        int i = n / 10;
        int j = n % 10;
        c++;
        if(c == 1) {
            if(resEt == 0) {
                final TextView timer = findViewById(R.id.timer);
                t = new CountDownTimer(timerValue, 1000) {
                    public void onTick(long millisUntilFinished) {
                        tim = millisUntilFinished/1000;
                        ringer.start();
                        ringer.setLooping(true);
                        timer.setText(millisUntilFinished / 1000 + "s");
                    }

                    public void onFinish() {
                        timer.setText("TIME UP");
                        TextView status = findViewById(R.id.status);
                        status.setText("Game Over");
                        timeOver++;
                        ringer.stop();
                        timerGone = MediaPlayer.create(PlayNow.this, R.raw.timergone);
                        timerGone.start();
                        tt++;
                    }
                }.start();
            }
            setMines(i, j);
        }
        if(marked) {
            if(arr[i][j] != 'A') {
                marked = false;
                arr[i][j] = 'M';
                show(i, j);
            }
        } else if (unmarked) {
            unmarked = false;
            if(arr[i][j] == 'M') {
                arr[i][j] = 'U';
            }
            img.setImageResource(0);
        } else {
            c++;
            if (arr[i][j] == 'N' || arr[i][j] == 'U') {
                if (c == 1) {
                    setMines(i, j);
                    openSurroundings(i, j);
                } else {
                    if (chkMine(i, j)) {
                        TextView status = findViewById(R.id.status);
                        status.setText("You Lost");
                        showBoard();
                        showBomb();
                        lost++;
                    } else {
                        openSurroundings(i, j);
                    }
                }
            }
        }
        if(lost == 0) {
            TextView status = findViewById(R.id.status);
            if (opened == (100 - bombNo)) {//Change for new number of bombs
                if (timeOver != 0) {
                    timeOver--;
                    status.setText("Win beyond time limit.");
                } else {
                    status.setText("Win");
                    if(unlockLevel <= 12) {
                        txt.setAlpha(1);
                    }
                    if(whetherEight == 0) {
                        dataBaseHandlerLevels db = new dataBaseHandlerLevels(PlayNow.this);
                        if (unlockLevel > db.showLevelNoEight()) {
                            level lev = new level(unlockLevel);
                            db.addLevelEight(lev);
                        }
                    } else {
                        dataBaseHandlerLevels db = new dataBaseHandlerLevels(PlayNow.this);
                        if (unlockLevel > db.showLevelNo()) {
                            level lev = new level(unlockLevel);
                            db.addLevel(lev);
                        }
                    }
                }
                fireworks = MediaPlayer.create(PlayNow.this, R.raw.fireworks);
                ringer.stop();
                fireworks.start();
                ImageView lpg = findViewById(R.id.fireworks);
                lpg.setImageResource(R.drawable.fireworks);
                //ringer.start();
                showBoard();
                showBomb();
            } else {
                if (! chkMine(i, j) && tt == 0) {
                    status.setText("Game in Progress....");
                } else {
                    if(arr[i][j] == 'M' || arr[i][j] == 'U') {
                        status.setText("Game in Progress....");
                    } else {
                        status.setText("Game Over");
                    }
                }
                TextView status1 = findViewById(R.id.statusBomb);
                status1.setText("Bombs: " + bombNo);//Change for new number of bombs
            }
        }
    }
    private void showBomb() {
        for(int i = 0; i < bombNo; i++) {//Change for new number of bombs
            int k = unique[i][0] * 10 + unique[i][1] + 2;
            String s = String.format("imageView%d",k);
            t.cancel();
            ringer.stop();
            final ImageView img = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
            if(opened == (100 - bombNo)) {//Change for new number of bombs
                img.setImageResource(R.drawable.bomb);
                t.cancel();
            } else {
                img.setImageResource(R.drawable.bomb);
                final int finalI = i;
                new CountDownTimer(1000, 1000) { // 5000 = 5 sec

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        if(finalI == 0) {
                            ring = MediaPlayer.create(PlayNow.this, R.raw.ring);
                            ring.start();
                        }
                        img.setImageResource(R.drawable.exploded);
                    }
                }.start();
            }
        }
    }
    private void setMines(int l, int m) {
        Random rand = new Random();
        for(int i = 0; i < bombNo; i++) {//Change for new number of bombs
            unique[i][0]=rand.nextInt(10);//getting random rows and columns through rand.nextInt()
            unique[i][1]=rand.nextInt(10);
            a[i] = unique[i][0];
            b[i] = unique[i][1];
            if(i != 0) {
                if (not_unique_combo(unique, i) || (unique[i][0] == l && unique[i][1] == m)) {
                    i--;
                }
            } else {
                if(unique[i][0] == l && unique[i][1] == m) {
                    i--;
                }
            }
        }
        for(int i = 0; i < bombNo; i++) {
            arrData[unique[i][0]][unique[i][1]] = 111;
        }
        setSurroundings();
    }
    private boolean not_unique_combo(int[][] unique, int n) {
        for(int i = 0; i <= n; i++) {
            for(int j = i + 1; j <= n; j++) {
                if(unique[i][0] == unique[j][0] && unique[i][1] == unique[j][1]) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean chkMine(int i , int j) {
        if(arrData[i][j] == 111) {
            return true;
        }
        return false;
    }
    private void showBoard() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                show(i, j);
            }
        }
    }
    private void openSurroundings(int i, int j) {
        if(whetherEight == 0) {
            arr[i][j] = 'O';
            opened++;
            if (i - 1 >= 0 && arrData[i - 1][j] != 111 && arr[i - 1][j] != 'A' && arr[i - 1][j] != 'M') {
                arr[i - 1][j] = 'O';
                opened++;
            }
            if (j - 1 >= 0 && arrData[i][j - 1] != 111 && arr[i][j - 1] != 'A' && arr[i][j - 1] != 'M') {
                arr[i][j - 1] = 'O';
                opened++;
            }
            if (i + 1 < 10 && arrData[i + 1][j] != 111 && arr[i + 1][j] != 'A' && arr[i + 1][j] != 'M') {
                arr[i + 1][j] = 'O';
                opened++;
            }
            if (j + 1 < 10 && arrData[i][j + 1] != 111 && arr[i][j + 1] != 'A' && arr[i][j + 1] != 'M') {
                arr[i][j + 1] = 'O';
                opened++;
            }
            if (i - 1 >= 0 && j - 1 >= 0 && arrData[i - 1][j - 1] != 111 && arr[i - 1][j - 1] != 'A' && arr[i - 1][j - 1] != 'M') {
                arr[i - 1][j - 1] = 'O';
                opened++;
            }
            if (i - 1 >= 0 && j + 1 < 10 && arrData[i - 1][j + 1] != 111 && arr[i - 1][j + 1] != 'A' && arr[i - 1][j + 1] != 'M') {
                arr[i - 1][j + 1] = 'O';
                opened++;
            }
            if (j - 1 >= 0 && i + 1 < 10 && arrData[i + 1][j - 1] != 111 && arr[i + 1][j - 1] != 'A' && arr[i + 1][j - 1] != 'M') {
                arr[i + 1][j - 1] = 'O';
                opened++;
            }
            if (i + 1 < 10 && j + 1 < 10 && arrData[i + 1][j + 1] != 111 && arr[i + 1][j + 1] != 'A' && arr[i + 1][j + 1] != 'M') {
                arr[i + 1][j + 1] = 'O';
                opened++;
            }
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < 10; l++) {
                    if (arr[k][l] == 'O') {
                        show(k, l);
                    }
                }
            }
            TextView status = findViewById(R.id.statusOpen);
            status.setText("Opened: " + opened);
        } else {
            arr[i][j] = 'O';
            opened++;
            if (arrData[i][j] == 0) {
                if (i - 1 >= 0 && arrData[i - 1][j] != 111 && arr[i - 1][j] != 'A' && arr[i - 1][j] != 'M' && arr[i - 1][j] != 'O') {
                    openSurroundings(i - 1, j);
                    arr[i - 1][j] = 'O';
                }
                if (j - 1 >= 0 && arrData[i][j - 1] != 111 && arr[i][j - 1] != 'A' && arr[i][j - 1] != 'M' && arr[i][j - 1] != 'O') {
                    openSurroundings(i, j - 1);
                    arr[i][j - 1] = 'O';
                }
                if (i + 1 < 10 && arrData[i + 1][j] != 111 && arr[i + 1][j] != 'A' && arr[i + 1][j] != 'M' && arr[i + 1][j] != 'O') {
                    openSurroundings(i + 1, j);
                    arr[i + 1][j] = 'O';
                }
                if (j + 1 < 10 && arrData[i][j + 1] != 111 && arr[i][j + 1] != 'A' && arr[i][j + 1] != 'M' && arr[i][j + 1] != 'O') {
                    openSurroundings(i, j + 1);
                    arr[i][j + 1] = 'O';
                }
                if (i - 1 >= 0 && j - 1 >= 0 && arrData[i - 1][j - 1] != 111 && arr[i - 1][j - 1] != 'A' && arr[i - 1][j - 1] != 'M' && arr[i - 1][j - 1] != 'O') {
                    openSurroundings(i - 1, j - 1);
                    arr[i - 1][j - 1] = 'O';
                }
                if (i - 1 >= 0 && j + 1 < 10 && arrData[i - 1][j + 1] != 111 && arr[i - 1][j + 1] != 'A' && arr[i - 1][j + 1] != 'M' && arr[i - 1][j + 1] != 'O') {
                    openSurroundings(i - 1, j + 1);
                    arr[i - 1][j + 1] = 'O';
                }
                if (j - 1 >= 0 && i + 1 < 10 && arrData[i + 1][j - 1] != 111 && arr[i + 1][j - 1] != 'A' && arr[i + 1][j - 1] != 'M' && arr[i + 1][j - 1] != 'O') {
                    openSurroundings(i + 1, j - 1);
                    arr[i + 1][j - 1] = 'O';
                }
                if (i + 1 < 10 && j + 1 < 10 && arrData[i + 1][j + 1] != 111 && arr[i + 1][j + 1] != 'A' && arr[i + 1][j + 1] != 'M' && arr[i + 1][j + 1] != 'O') {
                    openSurroundings(i + 1, j + 1);
                    arr[i + 1][j + 1] = 'O';
                }
            }
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < 10; l++) {
                    if (arr[k][l] == 'O') {
                        show(k, l);
                    }
                }
            }
            TextView status = findViewById(R.id.statusOpen);
            status.setText("Opened: " + opened);
        }
    }
    private void setSurroundings() {
        for(int i = 0; i < bombNo; i++) { //updating mine in surroundings
            if(a[i] - 1 >= 0) {//Change for new number of bombs
                if(arrData[a[i] - 1][b[i]] != 111) {
                    arrData[a[i] - 1][b[i]] += 1;
                }
            }
            if(b[i] - 1 >= 0) {
                if(arrData[a[i]][b[i] - 1] != 111) {
                    arrData[a[i]][b[i] - 1] += 1;
                }
            }
            if(a[i] + 1 < 10) {
                if(arrData[a[i] + 1][b[i]] != 111) {
                    arrData[a[i] + 1][b[i]] += 1;
                }
            }
            if(b[i] + 1 < 10) {
                if(arrData[a[i]][b[i] + 1] != 111) {
                    arrData[a[i]][b[i] + 1] += 1;
                }
            }
            if(a[i] + 1 < 10 && b[i] + 1 < 10) {
                if(arrData[a[i] + 1][b[i] + 1] != 111) {
                    arrData[a[i] + 1][b[i] + 1] += 1;
                }
            }
            if(a[i] - 1 >= 0 && b[i] + 1 < 10) {
                if(arrData[a[i] - 1][b[i] + 1] != 111) {
                    arrData[a[i] - 1][b[i] + 1] += 1;
                }
            }
            if(b[i] - 1 >= 0 && a[i] + 1 < 10) {
                if(arrData[a[i] + 1][b[i] - 1] != 111) {
                    arrData[a[i] + 1][b[i] - 1] += 1;
                }
            }
            if(a[i] - 1 >= 0 && b[i] - 1 >= 0) {
                if(arrData[a[i] - 1][b[i] - 1] != 111) {
                    arrData[a[i] - 1][b[i] - 1] += 1;
                }
            }
        }
    }
    public void mark(View view) {
        TextView status = findViewById(R.id.status);
        status.setText("Mark a Cell.");
        marked = true;
    }
    public void unmark(View view) {
        TextView status = findViewById(R.id.status);
        status.setText("Un-mark the Marked cell(s).");
        unmarked = true;
    }
    private void show(int i, int j) {
        int k = i * 10 + j + 2;
        String s = String.format("imageView%d",k);
        ImageView img = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
        if(arr[i][j] == 'M') {
            img.setImageResource(R.drawable.flag);
        } else if (arrData[i][j] == 0) {
            img.setImageResource(R.drawable.zero);
        } else if (arrData[i][j] == 1) {
            img.setImageResource(R.drawable.one);
        } else if (arrData[i][j] == 2) {
            img.setImageResource(R.drawable.two);
        } else if (arrData[i][j] == 3) {
            img.setImageResource(R.drawable.three);
        } else if (arrData[i][j] == 4) {
            img.setImageResource(R.drawable.four);
        } else if (arrData[i][j] == 5) {
            img.setImageResource(R.drawable.five);
        } else if (arrData[i][j] == 6) {
            img.setImageResource(R.drawable.six);
        } else if (arrData[i][j] == 7) {
            img.setImageResource(R.drawable.seven);
        } else if (arrData[i][j] == 8) {
            img.setImageResource(R.drawable.eight);
        }
        img.animate().setDuration(300);
        if(arr[i][j] != 'M') {
            arr[i][j] = 'A';
        }
    }
    public void reset(View view) {
        resEt++;
        if(ringer != null) {
            ringer.stop();
            ringer.release();
        }
        if(t != null) {
            t.cancel();
        }
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                arr[i][j] = 'N';//N means not opened
                arrData[i][j] = 0;
                int k = i * 10 + j + 2;
                String s = String.format("imageView%d", k);
                ImageView img = (ImageView) findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                img.setImageResource(0);
            }
        }
        ImageView lpg = findViewById(R.id.fireworks);
        lpg.setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText(" ");
        TextView status1 = findViewById(R.id.statusOpen);
        status1.setText(" ");
        TextView status2 = findViewById(R.id.statusBomb);
        status2.setText(" ");
        if(opened == (100 - bombNo)) {//Change for new number of bombs
            fireworks.stop();
            fireworks.release();
        }
        opened = 0;
        c = 0;
        marked = false;
        unmarked = false;
        tt = 0;
        lost = 0;
        txt.setAlpha(0);
        final TextView timer = findViewById(R.id.timer);
        //t.cancel();
        t = new CountDownTimer(timerValue, 1000) {
            public void onTick(long millisUntilFinished) {
                tim = millisUntilFinished/1000;
                timer.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                timer.setText("TIME UP");
                TextView status = findViewById(R.id.status);
                status.setText("Game Over");
                timeOver++;
                timerGone = MediaPlayer.create(PlayNow.this, R.raw.timergone);
                timerGone.start();
                ringer.stop();
                tt++;
            }
        }.start();
        ringer = MediaPlayer.create(PlayNow.this,R.raw.ringer);
        ringer.start();
        ringer.setLooping(true);
    }
    public void doNothing(View view) {

    }
    public void next(View view) {
        if(txt.getAlpha() == 1 && unlockLevel <= 12) {
            onBackPressed();
        }
    }
    public void onBackPressed() {
        if(whetherEight == 0) {
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
        if(ringer != null) {
            ringer.stop();
        }
        if(fireworks != null) {
            fireworks.stop();
        }
        if(timerGone != null) {
            timerGone.stop();
        }
        if(t != null) {
            t.cancel();
        }
        if(ring != null) {
            ring.stop();
        }
        PerfectLoopMediaPlayer.create(PlayNow.this, R.raw.bg);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_now);
        PerfectLoopMediaPlayer.release();
        Intent intent = getIntent();
        bombNo = intent.getIntExtra("bombNo", 20);
        timerValue = intent.getIntExtra("timeGiven", 120000);
        unlockLevel = intent.getIntExtra("unlock", 12);
        whetherEight = intent.getIntExtra("isEight", 0);
        viewScreen = findViewById(R.id.screen);
        txt = findViewById(R.id.nextLev);

        /*chooseTheme ct = new chooseTheme();
        if(ct.provideTheme() != null) {
            city = ct.provideTheme();
        } else {
            city = "";
        }*/
        DataBaseHandlerMT dataBaseHandlerMT = new DataBaseHandlerMT(PlayNow.this);
        int n;
        try {
            n = dataBaseHandlerMT.showTheme();
            Log.d("Msg", "ntry="+n);
        } catch(Exception e) {
            n = 9;
            try {
                dataBaseHandlerMT.addTheme(new MusicThemeT(9));
            } catch(Exception ed) {

            }
        }
        imgGrid = findViewById(R.id.gridImage);
        if(n == 1) {
            imgGrid.setImageResource(R.drawable.mumbai2);
            viewScreen.setBackgroundResource(R.drawable.blu);
            //viewScreen.setBackgroundColor(Color.parseColor("#0080FF"));
        } else if (n == 2) {
            imgGrid.setImageResource(R.drawable.chennai2);
            viewScreen.setBackgroundResource(R.drawable.darkblue);
            //viewScreen.setBackgroundColor(Color.parseColor("#99D6FF"));
        } else if (n == 6) {
            imgGrid.setImageResource(R.drawable.bangalore2);
            viewScreen.setBackgroundResource(R.drawable.golden);
            //viewScreen.setBackgroundColor(Color.parseColor("#FFE066"));
        } else if (n == 5) {
            imgGrid.setImageResource(R.drawable.rajasthan2);
            viewScreen.setBackgroundResource(R.drawable.pink);
            //viewScreen.setBackgroundColor(Color.parseColor("#FFB3D1"));
        } else if (n == 4) {
            imgGrid.setImageResource(R.drawable.hyderabad2);
            viewScreen.setBackgroundResource(R.drawable.orange);
            //viewScreen.setBackgroundColor(Color.parseColor("#FFB380"));
        } else if (n == 8) {
            imgGrid.setImageResource(R.drawable.delhi2);
            viewScreen.setBackgroundResource(R.drawable.blue);
            //viewScreen.setBackgroundColor(Color.parseColor("#66A3FF"));
        } else if (n == 7) {
            imgGrid.setImageResource(R.drawable.punjab2);
            viewScreen.setBackgroundResource(R.drawable.red);
            //viewScreen.setBackgroundColor(Color.parseColor("#FF6666"));
        } else if (n == 3) {
            imgGrid.setImageResource(R.drawable.kolkata2);
            viewScreen.setBackgroundResource(R.drawable.purple);
            //viewScreen.setBackgroundColor(Color.parseColor("#DD80FF"));
        } else {
            imgGrid.setImageResource(R.drawable.grid2);
            viewScreen.setBackgroundResource(R.drawable.themeback);
            //viewScreen.setBackgroundColor(Color.parseColor("#00CC99"));
        }
        ringer = MediaPlayer.create(PlayNow.this,R.raw.ringer);
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                arr[i][j] = 'N';//N means not opened
                arrData[i][j] = 0;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ringer != null) {
            ringer.stop();
        }
        if(fireworks != null) {
            fireworks.stop();
        }
        if(timerGone != null) {
            timerGone.stop();
        }
        if(t != null) {
            t.cancel();
        }
        if(ring != null) {
            ring.stop();
        }
    }

}