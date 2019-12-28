package com.strzal.hungry.entity.makers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class CookingPlaceButton extends BasicMakerButton {


    public CookingPlaceButton(HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.COOKING_PLACE_TIME;

    }

    @Override
    protected void loadTextures() {
        textureUp = assetManager.get(ImagesPaths.COOKING_PLACE_01);
        textureDown = assetManager.get(ImagesPaths.COOKING_PLACE_02);
        textureChecked = assetManager.get(ImagesPaths.COOKING_PLACE_03);
    }

    @Override
    protected void buttonClicked() {
        if (canPressAgain) {
            canPressAgain = false;
            imageButton.setTouchable(Touchable.disabled);

            if (!isButtonPressed && gameController.isPossibleMakeMoreBools()) {
                isButtonPressed = !isButtonPressed;
                System.out.println("Cooking Place Clicked");
                initialClickTime = TimeUtils.millis();
                gameController.useEnergy(GameSetting.COOKING_PLACE_USED_ENERGY);
            }
        }
    }

    @Override
    protected void update() {
        if (isButtonPressed) {
            if (checkCookingTimeHasPassed()) {
                isButtonPressed = false;
                canPressAgain = true;
                gameController.addBool();
                imageButton.setTouchable(Touchable.enabled);
                imageButton.setChecked(false);
            }
        }
        // always set disabled if cannot click
        else if (gameController.isPossibleMakeMoreBools()) {
            imageButton.setTouchable(Touchable.enabled);
        } else {
            imageButton.setTouchable(Touchable.disabled);
        }
    }


}
