package com.vaibhav.minesweeper.Model;

public class highScore {
    private String time;
    private String date;
    private int id;

    public highScore(String time, String date, int id) {
        this.time = time;
        this.date = date;
        this.id = id;
    }

    public highScore(String time, String date) {
        this.time = time;
        this.date = date;
    }

    public highScore() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
