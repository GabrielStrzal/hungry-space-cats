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

public class FishEntity extends BasicButton {

    private ImageButton fishButton;

    public FishEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        gameController.setCurrentFishEntity(this);

        fishButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.FISH)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.FISH_PRESSED))
        );


        fishButton.setPosition(xPosition, yPosition);
        fishButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        stage.addActor(fishButton);
    }

    private void buttonClicked() {
        if(gameController.useFish()){
            gameController.setCurrentFishEntity(null);
            fishButton.remove();
        };
    }
}
