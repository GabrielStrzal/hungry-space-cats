package com.strzal.hungry.entity.food;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class BoolSmallEntity extends FoodEntity {

    public BoolSmallEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition, arrayPosition);

        gameController.getBoolSmallEntityList().add(this);
    }

    @Override
    protected void loadTextures() {
        texture = assetManager.get(ImagesPaths.BOOL_02);
        textureUp = assetManager.get(ImagesPaths.BOOL_02_PRESSED);
    }

    public int boolUsed() {
        gameController.getBoolSmallEntityList().remove(this);
        imageButton.remove();
        return arrayPosition;
    }
}
