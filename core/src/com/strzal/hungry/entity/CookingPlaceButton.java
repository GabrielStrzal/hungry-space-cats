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

public class CookingPlaceButton extends BasicButton {

    private Image currentCookingPlaceImage;

    private boolean isCookingPlacePressed = false;

    private Texture cookingPlace2 = assetManager.get(ImagesPaths.COOKING_PLACE_02);
    private Texture cookingPlacePressed = assetManager.get(ImagesPaths.COOKING_PLACE_03);




    public CookingPlaceButton(BasicGame game, final GameController gameController, Stage stage, int xPosition, int yPosition){
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.COOKING_PLACE_TIME;


        ImageButton transparentCookingPlace = buttonsUtils.getTransparentImageButton(xPosition, yPosition, 80, 70);

        transparentCookingPlace.setPosition(xPosition, yPosition);
        transparentCookingPlace.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        currentCookingPlaceImage = new Image(cookingPlacePressed);
        currentCookingPlaceImage.setPosition(xPosition, yPosition);

        stage.addActor(currentCookingPlaceImage);

        //Transparent buttons
        stage.addActor(transparentCookingPlace);
    }

    private void buttonClicked() {

        if(!isCookingPlacePressed && gameController.isPossibleMakeMoreBools()){
            isCookingPlacePressed = !isCookingPlacePressed;
            System.out.println("Cooking Place Clicked");
            initialClickTime = TimeUtils.millis();
            gameController.useEnergy(GameSetting.COOKING_PLACE_USED_ENERGY);
        }
    }

    @Override
    public void render(){
       update();
    }


    private void update(){


        if(isCookingPlacePressed){
            if(checkCookingTimeHasPassed()) {
                isCookingPlacePressed = !isCookingPlacePressed;
                gameController.addBool();
            }else{
                currentCookingPlaceImage.setDrawable(new TextureRegionDrawable(cookingPlacePressed));
            }
        }else{
            currentCookingPlaceImage.setDrawable(new TextureRegionDrawable(cookingPlace2));
        }
    }


}
