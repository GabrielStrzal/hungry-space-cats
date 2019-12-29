package com.strzal.hungry.config;


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

    public int getWave() {
        return wave;
    }

    public int getCash() {
        return cash;
    }
}
