package com.strzal.hungry.controller;

import com.strzal.hungry.entity.WaterEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameController {

    @Setter
    private long energy;
    @Setter
    private long oxygen;
    private int cash;

    private int water = 0;
    private List<WaterEntity> waterEntityList;

    public GameController(int cash){
        this.cash = cash;
        oxygen = 100;
        energy = 100;
        waterEntityList = new ArrayList<>();
    }

    public void addCash(int plusCash){
        cash += plusCash;
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

    public void addWatter() {
        water++;
    }

    public void useEnergy(int usedEnergy) {
        energy -= usedEnergy;
    }

    public void useWater() {
        water -= 1;
    }

    public boolean isPossibleMakeMoreWater(){
        return water < 2;
    }

    public boolean isPositionOneEmpty() {
        if(getWaterEntityList().isEmpty()){
            return true;
        }

        for (WaterEntity entity : waterEntityList) {
            if(entity.getArrayPosition() == 1)
                return false;
        }
        return true;
    }
}
