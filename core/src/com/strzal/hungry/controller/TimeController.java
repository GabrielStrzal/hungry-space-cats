package com.strzal.hungry.controller;

import com.badlogic.gdx.utils.TimeUtils;

public class TimeController {

    private long levelStartTime;

    public TimeController(){
        levelStartTime = TimeUtils.millis();
    }

    public long getTimePassedInSeconds() {
        return (TimeUtils.millis() - levelStartTime)/1000;
    }
}
