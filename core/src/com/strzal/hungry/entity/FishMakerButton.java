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


public class FishMakerButton extends BasicButton {

    private Image currentFishMakerImage;

    private boolean isFishMakerPressed = false;

    private Texture fishMaker2 = assetManager.get(ImagesPaths.COOKING_FISH_PLACE_02);
    private Texture fishMakerPressed = assetManager.get(ImagesPaths.COOKING_FISH_PLACE_03);




    public FishMakerButton(BasicGame game, final GameController gameController, Stage stage, int xPosition, int yPosition){
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.FISH_MAKING_PLACE_TIME;

        ImageButton transparentFishMaker = buttonsUtils.getTransparentImageButton(xPosition, yPosition, fishMaker2.getWidth(), fishMaker2.getHeight());

        transparentFishMaker.setPosition(xPosition, yPosition);
        transparentFishMaker.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        currentFishMakerImage = new Image(fishMaker2);
        currentFishMakerImage.setPosition(xPosition, yPosition);

        stage.addActor(currentFishMakerImage);

        //Transparent buttons
        stage.addActor(transparentFishMaker);
    }

    private void buttonClicked() {

        if(!isFishMakerPressed && gameController.isPossibleMakeMoreFish()){
            isFishMakerPressed = !isFishMakerPressed;
            System.out.println("Fish Maker Clicked");
            initialClickTime = TimeUtils.millis();
            gameController.useEnergy(GameSetting.FISH_MAKER_PLACE_USED_ENERGY);
        }
    }

    @Override
    public void render(){
       update();
    }


    private void update(){


        if(isFishMakerPressed){
            if(checkCookingTimeHasPassed()) {
                isFishMakerPressed = !isFishMakerPressed;
                gameController.addFish();
            }else{
                currentFishMakerImage.setDrawable(new TextureRegionDrawable(fishMakerPressed));
            }
        }else{
            currentFishMakerImage.setDrawable(new TextureRegionDrawable(fishMaker2));
        }
    }


}
