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

public class FishBoolEntity extends BasicButton {

    private ImageButton fishBoolButton;
    @Getter
    private int arrayPosition;


    public FishBoolEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        this.arrayPosition = arrayPosition;

        gameController.getFishBoolEntityList().add(this);

        fishBoolButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_FISH)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_FISH_PRESSED))
        );


        fishBoolButton.setPosition(xPosition, yPosition);
        fishBoolButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        stage.addActor(fishBoolButton);
    }

    private void buttonClicked() {
        if(gameController.useFishBool()){
            gameController.getFishBoolEntityList().remove(this);
            fishBoolButton.remove();
        };
    }
}
