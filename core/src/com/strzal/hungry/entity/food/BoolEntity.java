package com.strzal.hungry.entity.food;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class BoolEntity extends FoodEntity {

    public BoolEntity(HungrySpaceCats game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition, arrayPosition);
        gameController.getBoolEntityList().add(this);
    }

    @Override
    protected void loadTextures() {
        texture = assetManager.get(ImagesPaths.BOOL_01);
        textureUp = assetManager.get(ImagesPaths.BOOL_01_PRESSED);
    }

    public int boolUsed() {
        gameController.getBoolEntityList().remove(this);
        imageButton.remove();
        return arrayPosition;
    }
}
