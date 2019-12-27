package com.strzal.hungry.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;


public class DrillEntity extends BasicEntity{

    private Image currentDrillImage;

    private boolean isDrillPressed = false;

    private Texture drill2 = assetManager.get(ImagesPaths.DRILL_02);
    private Texture drillPressed = assetManager.get(ImagesPaths.DRILL_03);




    public DrillEntity(BasicGame game, final GameController gameController, Stage stage, int xPosition, int yPosition){
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.DRILL_COOKING_TIME;


        ImageButton transparentDrill = buttonsUtils.getTransparentImageButton(xPosition, yPosition, 70, 70);

        transparentDrill.setPosition(xPosition, yPosition);
        transparentDrill.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        currentDrillImage = new Image(drill2);
        currentDrillImage.setPosition(xPosition, yPosition);

        stage.addActor(currentDrillImage);

        //Transparent buttons
        stage.addActor(transparentDrill);
    }

    private void buttonClicked() {

        if(!isDrillPressed && gameController.isPossibleMakeMoreWater()){
            isDrillPressed = !isDrillPressed;
            System.out.println("Drill Clicked");
            initialClickTime = TimeUtils.millis();
            gameController.useEnergy(GameSetting.DRILL_USED_ENERGY);
        }
    }

    @Override
    public void render(){
       update();
    }


    private void update(){


        if(isDrillPressed){
            if(checkCookingTimeHasPassed()) {
                isDrillPressed = !isDrillPressed;
                gameController.addChip();
            }else{
                currentDrillImage.setDrawable(new TextureRegionDrawable(drillPressed));
            }
        }else{
            currentDrillImage.setDrawable(new TextureRegionDrawable(drill2));
        }
    }


}
