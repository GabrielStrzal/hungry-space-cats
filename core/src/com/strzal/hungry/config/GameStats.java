package com.strzal.hungry.config;

import lombok.Getter;

@Getter
public class GameStats {
    private int wave = 1;
    private int cash;

    public void addWave(){
        wave++;
    }

    public void addCash(int addedCash){
        cash += addedCash;
    }

    public boolean useCash(int useCash){
        if (cash < useCash){
            return false;
        }
        else{
            cash -= useCash;
            return true;
        }
    }
}
