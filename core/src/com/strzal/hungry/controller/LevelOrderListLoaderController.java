package com.strzal.hungry.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.entity.HungryEntity;
import com.strzal.hungry.entity.OrderItemEnum;

import java.util.ArrayList;

public class LevelOrderListLoaderController {

    BasicGame game;
    Stage stage;
    AssetManager assetManager;

    public LevelOrderListLoaderController(BasicGame game, Stage stage){
        this.game = game;
        this.stage = stage;
        this.assetManager = game.getAssetManager();
    }


    public ArrayList<HungryEntity> getLevelList(int levelNumber){

        ArrayList<HungryEntity> hungryEntityArrayList = new ArrayList<>();

        switch (levelNumber){

            case 1:
                ArrayList<OrderItemEnum> orderItemEnumList = new ArrayList<>();
                orderItemEnumList.add(OrderItemEnum.WATER);
                orderItemEnumList.add(OrderItemEnum.FISH_BOOL);

                ArrayList<OrderItemEnum> orderItemEnumList2 = new ArrayList<>();
                orderItemEnumList2.add(OrderItemEnum.CHIPS_BOOL);
                orderItemEnumList2.add(OrderItemEnum.WATER);

                HungryEntity hungryEntity = new HungryEntity(game, stage, (Texture) assetManager.get(ImagesPaths.HUNGRY_CAT), orderItemEnumList);
                HungryEntity hungryEntity2 = new HungryEntity(game, stage, (Texture) assetManager.get(ImagesPaths.HUNGRY_CAT), orderItemEnumList2);
                hungryEntityArrayList.add(hungryEntity);
                hungryEntityArrayList.add(hungryEntity2);
            case 2:


        }
        return hungryEntityArrayList;

    }

}
