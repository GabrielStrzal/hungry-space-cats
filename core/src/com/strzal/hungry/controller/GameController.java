package com.strzal.hungry.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.strzal.hungry.audio.AudioHandler;
import com.strzal.hungry.config.GamePositions;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.config.GameStats;
import com.strzal.hungry.entity.*;
import com.strzal.hungry.entity.food.*;
import com.strzal.hungry.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;


public class GameController {

    GameScreen gameScreen;
    GameStats gameStats;
    AudioHandler audioHandler;

    private long energy;
    private long oxygen;

    private long oxygenForMeter;

    private int water = 0;
    private List<WaterEntity> waterEntityList;

    private ArrayList<HungryEntity> hungryEntityList;
    private HungryEntity currentHungryEntity;

    private boolean isCurrentLevelCompleted = false;
    private boolean isEndless = false;

    //chips
    private int chips = 0;
    private List<ChipEntity> chipEntityList;

    //bools
    private int bools = 0;
    private List<BoolEntity> boolEntityList;

    //fish
    private int fish = 0;
    private FishEntity currentFishEntity;


    //smallBool
    private int smallBools = 0;
    private List<BoolSmallEntity> boolSmallEntityList;

    //fishBool
    private int fishBool = 0;
    private List<FishBoolEntity> fishBoolEntityList;


    //chips
    private int chipBool = 0;
    private List<ChipBoolEntity> chipBoolEntityList;

    //gameOver
    private boolean gameOver;

    public GameController(ArrayList<HungryEntity> hungryEntityList, GameScreen gameScreen, GameStats gameStats, AudioHandler audioHandler){
        this.gameScreen = gameScreen;
        this.gameStats = gameStats;
        this.audioHandler = audioHandler;
        oxygen = 100;
        energy = 100;
        waterEntityList = new ArrayList<>();
        chipEntityList = new ArrayList<>();
        boolEntityList = new ArrayList<>();
        chipBoolEntityList = new ArrayList<>();
        boolSmallEntityList = new ArrayList<>();
        fishBoolEntityList = new ArrayList<>();

        this.hungryEntityList = hungryEntityList;

        oxygenForMeter = GameSetting.OXYGEN_INITIAL_TIME;
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
    public void addFish() {
        fish++;
    }
    public void addSmallBool() {
        smallBools++;
    }

    public void useEnergy(int usedEnergy) {
        energy -= usedEnergy;
    }

    public void addEnergy(int energyToAdd) {
        energy += energyToAdd;
        if(energy > 100){
            energy = 100;
        }
    }
    public void addOxygen(int oxygenToAdd) {
        oxygenForMeter += oxygenToAdd;
    }

    public boolean useWater() {
        if(isThereThisOrderInOrderList(OrderItemEnum.WATER)){
            water--;
            return true;
        }
        return false;
    }

    public boolean useFish() {

        if(smallBools > 0){
            smallBools--;
            fish--;
            fishBool++;
            int arrayPosition = boolSmallEntityList.get(0).boolUsed();
            if(arrayPosition == 1){
                gameScreen.createFishBoolEntity(GamePositions.BOOL_SMALL_Y_POSITION, 1);
            } else {
                gameScreen.createFishBoolEntity(GamePositions.BOOL_SMALL_Y_SECOND_POSITION, 2);
            }

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
                gameScreen.createChipBoolEntity(GamePositions.BOOL_Y_POSITION, 1);
            } else {
                gameScreen.createChipBoolEntity(GamePositions.BOOL_Y_SECOND_POSITION, 2);
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

    public boolean useFishBool() {
        if(isThereThisOrderInOrderList(OrderItemEnum.FISH_BOOL)){
            fishBool--;
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

    public boolean isPossibleMakeMoreFish() {
        return fish < 1;
    }
    public boolean isPossibleMakeMoreSmallBools() {
        return (smallBools + fishBool) < 2;
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
            if(entity.getArrayPosition() == 1) {
                return false;
            }
        }
        for (ChipBoolEntity entity : getChipBoolEntityList()) {
            if(entity.getArrayPosition() == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isBoolSmallPositionOneEmpty() {

        if(getBoolSmallEntityList().isEmpty() && getFishBoolEntityList().isEmpty()){
            return true;
        }

        for (BoolSmallEntity entity : getBoolSmallEntityList()) {
            if(entity.getArrayPosition() == 1) {
                return false;
            }
        }

        for (FishBoolEntity entity : getFishBoolEntityList()) {
            if(entity.getArrayPosition() == 1) {
                return false;
            }
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
            audioHandler.playOrderCompleteSound();
            hungryEntityList.remove(currentHungryEntity);
            gameStats.addCash(GameSetting.CASH_PER_ORDER);
            currentHungryEntity = null;
        }
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public AudioHandler getAudioHandler() {
        return audioHandler;
    }

    public long getEnergy() {
        return energy;
    }

    public long getOxygen() {
        return oxygen;
    }

    public long getOxygenForMeter() {
        return oxygenForMeter;
    }

    public int getWater() {
        return water;
    }

    public List<WaterEntity> getWaterEntityList() {
        return waterEntityList;
    }

    public ArrayList<HungryEntity> getHungryEntityList() {
        return hungryEntityList;
    }

    public HungryEntity getCurrentHungryEntity() {
        return currentHungryEntity;
    }

    public boolean isCurrentLevelCompleted() {
        return isCurrentLevelCompleted;
    }

    public int getChips() {
        return chips;
    }

    public List<ChipEntity> getChipEntityList() {
        return chipEntityList;
    }

    public int getBools() {
        return bools;
    }

    public List<BoolEntity> getBoolEntityList() {
        return boolEntityList;
    }

    public int getFish() {
        return fish;
    }

    public FishEntity getCurrentFishEntity() {
        return currentFishEntity;
    }

    public int getSmallBools() {
        return smallBools;
    }

    public List<BoolSmallEntity> getBoolSmallEntityList() {
        return boolSmallEntityList;
    }

    public int getFishBool() {
        return fishBool;
    }

    public List<FishBoolEntity> getFishBoolEntityList() {
        return fishBoolEntityList;
    }

    public int getChipBool() {
        return chipBool;
    }

    public List<ChipBoolEntity> getChipBoolEntityList() {
        return chipBoolEntityList;
    }

    public void setEnergy(long energy) {
        this.energy = energy;
    }

    public void setOxygen(long oxygen) {
        this.oxygen = oxygen;
    }

    public void setCurrentFishEntity(FishEntity currentFishEntity) {
        this.currentFishEntity = currentFishEntity;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isEndless() {
        return isEndless;
    }

    public void setEndless(boolean endless) {
        isEndless = endless;
    }
}
