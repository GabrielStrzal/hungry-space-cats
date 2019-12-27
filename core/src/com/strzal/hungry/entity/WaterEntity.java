package com.strzal.hungry.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import lombok.Getter;

public class WaterEntity extends BasicButton {

    private ImageButton watterButton;
    @Getter
    private int arrayPosition;


    public WaterEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        this.arrayPosition = arrayPosition;

        gameController.getWaterEntityList().add(this);

        watterButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.WATTER)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.WATTER_PRESSED))
        );


        watterButton.setPosition(xPosition, yPosition);
        watterButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        stage.addActor(watterButton);
    }

    private void buttonClicked() {
        if(gameController.useWater()){
            gameController.getWaterEntityList().remove(this);
            watterButton.remove();
        };
    }
}
