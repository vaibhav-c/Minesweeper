package com.vaibhav.minesweeper.Model;

public class highScore {
    private long score;
    private String date;
    private int id;
    public highScore(long score, String date, int id) {
        this.score = score;
        this.date = date;
        this.id = id;
    }

    public highScore(long score, String date) {
        this.score = score;
        this.date = date;
    }

    public highScore() {
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
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
