package com.strzal.hungry.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strzal.gdx.BasicGame;

import java.util.ArrayList;

public class HungryEntity {


    private Texture hungryEntityTexture;
    private Stage stage;
    private AssetManager assetManager;

    private ArrayList<OrderItemEnum> orderList;

    public HungryEntity(BasicGame game, Stage stage, Texture hungryEntityTexture, ArrayList<OrderItemEnum> orderList){
        this.stage = stage;
        this.hungryEntityTexture = hungryEntityTexture;
        this.orderList = orderList;
        this.assetManager = game.getAssetManager();

    }


    public boolean hasOrderBeenCompleted(){
        return orderList.isEmpty();
    }

    public boolean deliverItemInOrder(OrderItemEnum orderItemEnum){
        for (OrderItemEnum entity: orderList) {
            if(entity.equals(orderItemEnum)){
                orderList.remove(entity);
                return true;
            }
        }
        return false;
    }

    public void render(Batch batch){

        batch.begin();
        renderHungryEntity(batch);
        renderOrderList(batch);
        batch.end();

    }

    private void renderHungryEntity(Batch batch) {
        batch.draw(hungryEntityTexture, 300, 340);
    }
    private void renderOrderList(Batch batch) {
        int xPosition = 420;
        for (OrderItemEnum entity: orderList) {
            batch.draw((Texture) assetManager.get(entity.getImagePath()), xPosition, 355 );
            xPosition += 40;
        }

    }

    public void finishAndDistroy() {
        //add reward

        //remove self
    }
}
