package com.strzal.hungry.controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.strzal.hungry.entity.ChipEntity;
import com.strzal.hungry.entity.HungryEntity;
import com.strzal.hungry.entity.OrderItemEnum;
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

    private ArrayList<HungryEntity> hungryEntityList;
    private HungryEntity currentHungryEntity;

    private boolean isCurrentLevelCompleted = false;

    //chips
    private int chips = 0;
    private List<ChipEntity> chipEntityList;

    public GameController(int cash, ArrayList<HungryEntity> hungryEntityList){
        this.cash = cash;
        oxygen = 100;
        energy = 100;
        waterEntityList = new ArrayList<>();
        chipEntityList = new ArrayList<>();
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

    public void useEnergy(int usedEnergy) {
        energy -= usedEnergy;
    }

    public boolean useWater() {
        if(isThereThisOrderInOrderList(OrderItemEnum.WATER)){
            water -= 1;
            return true;
        }
        return false;
    }

    public boolean useChips() {
        if(isThereThisOrderInOrderList(OrderItemEnum.CHIPS_BOOL)){
            chips -= 1;
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
