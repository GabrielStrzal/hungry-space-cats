package com.strzal.hungry.handler;


public class LevelStats {
    private int totalTimesPlayed;
    private int week;
    private int highScore;
    private boolean isGameWon;

    public LevelStats(int totalTimesPlayed, int week, int highScore, boolean isGameWon) {
        this.totalTimesPlayed = totalTimesPlayed;
        this.week = week;
        this.highScore = highScore;
        this.isGameWon = isGameWon;
    }

    public int getTotalTimesPlayed() {
        return totalTimesPlayed;
    }

    public void setTotalTimesPlayed(int totalTimesPlayed) {
        this.totalTimesPlayed = totalTimesPlayed;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }
}
