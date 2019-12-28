package com.strzal.hungry.entity.makers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;


public class FishMakerButton extends BasicMakerButton {

    public FishMakerButton(HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.FISH_MAKING_PLACE_TIME;

    }

    @Override
    protected void loadTextures() {
        textureUp = assetManager.get(ImagesPaths.COOKING_FISH_PLACE_01);
        textureDown = assetManager.get(ImagesPaths.COOKING_FISH_PLACE_02);
        textureChecked = assetManager.get(ImagesPaths.COOKING_FISH_PLACE_03);
    }


    @Override
    protected void buttonClicked() {
        if (canPressAgain) {
            canPressAgain = false;
            imageButton.setTouchable(Touchable.disabled);

            if (!isButtonPressed && gameController.isPossibleMakeMoreFish()) {
                isButtonPressed = !isButtonPressed;
                System.out.println("Fish Maker Clicked");
                initialClickTime = TimeUtils.millis();
                gameController.useEnergy(GameSetting.FISH_MAKER_PLACE_USED_ENERGY);
            }
        }
    }


    @Override
    protected void update() {
        if (isButtonPressed) {
            if (checkCookingTimeHasPassed()) {
                isButtonPressed = false;
                canPressAgain = true;
                gameController.addFish();
                imageButton.setTouchable(Touchable.enabled);
                imageButton.setChecked(false);
            }
        }
        // always set disabled if cannot click
        else if (gameController.isPossibleMakeMoreFish()) {
            imageButton.setTouchable(Touchable.enabled);
        } else {
            imageButton.setTouchable(Touchable.disabled);
        }
    }


}
