package com.strzal.hungry.entity.food;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class WaterEntity extends FoodEntity {

    public WaterEntity(HungrySpaceCats game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition, arrayPosition);

        gameController.getWaterEntityList().add(this);

        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });
    }

    @Override
    protected void loadTextures() {
        texture = assetManager.get(ImagesPaths.WATTER);
        textureUp = assetManager.get(ImagesPaths.WATTER_PRESSED);
    }

    private void buttonClicked() {
        if (gameController.useWater()) {
            game.getAudioHandler().playKittenSound();
            gameController.getWaterEntityList().remove(this);
            imageButton.remove();
        }
    }
}
