package com.strzal.hungry.entity.food;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class FishEntity extends FoodEntity {


    public FishEntity(HungrySpaceCats game, GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition, 1);

        gameController.setCurrentFishEntity(this);

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
        texture = assetManager.get(ImagesPaths.FISH);
        textureUp = assetManager.get(ImagesPaths.FISH_PRESSED);
    }

    private void buttonClicked() {
        if (gameController.useFish()) {
            game.getAudioHandler().playHappySound();
            gameController.setCurrentFishEntity(null);
            imageButton.remove();
        }
    }
}
