package com.strzal.hungry.entity.food;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class ChipEntity extends FoodEntity {


    public ChipEntity(HungrySpaceCats game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition, arrayPosition);

        gameController.getChipEntityList().add(this);

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
        texture = assetManager.get(ImagesPaths.CHIPS);
        textureUp = assetManager.get(ImagesPaths.CHIPS_PRESSED);
    }

    private void buttonClicked() {
        if (gameController.useChips()) {
            game.getAudioHandler().plaHappySound();
            gameController.getChipEntityList().remove(this);
            imageButton.remove();
        }
    }
}
