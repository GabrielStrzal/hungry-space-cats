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
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class PumpEntity extends BasicEntity{

    private Image currentWatterPumpImage;

    private boolean isWatterPumpPressed = false;

    private Texture watterDrill2 = assetManager.get(ImagesPaths.WATTER_DRILL_02);
    private Texture watterDrillPressed = assetManager.get(ImagesPaths.WATTER_DRILL_03);




    public PumpEntity(BasicGame game, final GameController gameController, Stage stage, int xPosition, int yPosition){
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = 10; //seconds


        ImageButton transparentWatterPump = buttonsUtils.getTransparentImageButton(xPosition, yPosition, 70, 70);

        transparentWatterPump.setPosition(xPosition, yPosition);
        transparentWatterPump.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        currentWatterPumpImage = new Image(watterDrillPressed);
        currentWatterPumpImage.setPosition(xPosition, yPosition);

        stage.addActor(currentWatterPumpImage);

        //Transparent buttons
        stage.addActor(transparentWatterPump);
    }

    private void buttonClicked() {

        if(!isWatterPumpPressed && gameController.isPossibleMakeMoreWater()){
            isWatterPumpPressed = !isWatterPumpPressed;
            System.out.println("Clicked");
            initialClickTime = TimeUtils.millis();
            gameController.useEnergy(10);
        }
    }

    @Override
    public void render(){
       update();
    }


    private void update(){


        if(isWatterPumpPressed){
            if(checkCookingTimeHasPassed()) {
                isWatterPumpPressed = !isWatterPumpPressed;
                gameController.addWatter();
            }else{
                currentWatterPumpImage.setDrawable(new TextureRegionDrawable(watterDrillPressed));
            }
        }else{
            currentWatterPumpImage.setDrawable(new TextureRegionDrawable(watterDrill2));
        }
    }


}
