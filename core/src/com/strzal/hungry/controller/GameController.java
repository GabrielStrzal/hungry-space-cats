package com.strzal.hungry.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.strzal.hungry.config.GamePositions;
import com.strzal.hungry.entity.*;
import com.strzal.hungry.screens.GameScreen;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameController {

    GameScreen gameScreen;

    @Setter
    private long energy;
    @Setter
    private long oxygen;
    private int cash;

    private int water = 0;
    private List<WaterEntity> waterEntityList;

    private ArrayList<HungryEntity> hungryEntityList;
    private HungryEntity currentHungryEntity;

    private boolean isCurrentLevelCompleted = false;

    //chips
    private int chips = 0;
    private List<ChipEntity> chipEntityList;

    //bools
    private int bools = 0;
    private List<BoolEntity> boolEntityList;


    //chips
    private int chipBool = 0;
    private List<ChipBoolEntity> chipBoolEntityList;

    public GameController(int cash, ArrayList<HungryEntity> hungryEntityList, GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.cash = cash;
        oxygen = 100;
        energy = 100;
        waterEntityList = new ArrayList<>();
        chipEntityList = new ArrayList<>();
        boolEntityList = new ArrayList<>();
        chipBoolEntityList = new ArrayList<>();

        this.hungryEntityList = hungryEntityList;
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
    public void addChip() {
        chips++;
    }
    public void addBool() {
        bools++;
    }

    public void useEnergy(int usedEnergy) {
        energy -= usedEnergy;
    }

    public boolean useWater() {
        if(isThereThisOrderInOrderList(OrderItemEnum.WATER)){
            water--;
            return true;
        }
        return false;
    }

    public boolean useChips() {
        if(bools > 0){
            bools--;
            chips--;
            chipBool++;
            int arrayPosition = boolEntityList.get(0).boolUsed();
            if(arrayPosition == 1){
                chipBoolEntityList.add(gameScreen.createChipBoolEntity(GamePositions.BOOL_Y_POSITION, arrayPosition));
            } else {
                chipBoolEntityList.add(gameScreen.createChipBoolEntity(GamePositions.BOOL_Y_SECOND_POSITION, arrayPosition));
            }

            return true;
        }
        return false;
    }

    public boolean useChipBool() {
        if(isThereThisOrderInOrderList(OrderItemEnum.CHIPS_BOOL)){
            chipBool--;
            return true;
        }
        return false;
    }


    private boolean isThereThisOrderInOrderList(OrderItemEnum orderItemEnum){

        if(currentHungryEntity.deliverItemInOrder(orderItemEnum)){
            return true;
        }
        return false;
    }


    public boolean isPossibleMakeMoreWater(){
        return water < 2;
    }
    public boolean isPossibleMakeMoreChips(){
        return chips < 2;
    }

    public boolean isPossibleMakeMoreBools() {
        return (bools + chipBool) < 2;
    }

    public boolean isWaterPositionOneEmpty() {
        if(getWaterEntityList().isEmpty()){
            return true;
        }

        for (WaterEntity entity : waterEntityList) {
            if(entity.getArrayPosition() == 1)
                return false;
        }
        return true;
    }

    public boolean isChipPositionOneEmpty() {
        if(getChipEntityList().isEmpty()){
            return true;
        }

        for (ChipEntity entity : getChipEntityList()) {
            if(entity.getArrayPosition() == 1)
                return false;
        }
        return true;
    }
    public boolean isBoolPositionOneEmpty() {
        if(getBoolEntityList().isEmpty() && getChipBoolEntityList().isEmpty()){
            return true;
        }

        for (BoolEntity entity : getBoolEntityList()) {
            if(entity.getArrayPosition() == 1)
                return false;
        }
        for (ChipBoolEntity entity : getChipBoolEntityList()) {
            if(entity.getArrayPosition() == 1)
                return false;
        }
        return true;
    }


    public void render(Batch batch){
        if(hungryEntityList.isEmpty()){
            isCurrentLevelCompleted = true;
        } else {
            renderHungryEntities(batch);
        }

    }

    private void renderHungryEntities(Batch batch){
        if(currentHungryEntity == null) {
            currentHungryEntity = hungryEntityList.get(0);
        }

        currentHungryEntity.render(batch);

        if(currentHungryEntity.hasOrderBeenCompleted()){
            hungryEntityList.remove(currentHungryEntity);
            addCash(100);
            currentHungryEntity = null;
        }
    }


}
