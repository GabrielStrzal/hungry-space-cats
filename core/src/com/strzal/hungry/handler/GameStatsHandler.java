package com.strzal.hungry.handler;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameStatsHandler {

    Preferences prefs = Gdx.app.getPreferences("hungry_cats_v1_0");
    //Preferences gameSettings = Gdx.app.getPreferences("hungry_cats_settings_v1_0");

    public GameStatsHandler() {
    }

    public void saveLevelData(LevelStats levelStats){

        //total plays
        int totalTimesPlayed = prefs.getInteger("totalTimesPlayed", 0);
        prefs.putInteger("totalTimesPlayed", ++totalTimesPlayed);

        //top week
        int topWeek = prefs.getInteger("topWeek", 0);
        if(topWeek < levelStats.getWeek()) {
            prefs.putInteger("topWeek", levelStats.getWeek());
        }

        //high score
        int highScore = prefs.getInteger("highScore", 0);
        if(highScore < levelStats.getHighScore()) {
            prefs.putInteger("highScore", levelStats.getHighScore());
        }

        //isGameWon
        boolean isGameWon = prefs.getBoolean("isGameWon", false);
        if(!isGameWon) {
            prefs.putBoolean("isGameWon", levelStats.isGameWon());
        }

        prefs.flush();
    }

    public LevelStats getSavedData() {
        return new LevelStats(
                prefs.getInteger("totalTimesPlayed", 0),
                prefs.getInteger("topWeek", 0),
                prefs.getInteger("highScore", 0),
                prefs.getBoolean("isGameWon", false)
        );
    }
}
