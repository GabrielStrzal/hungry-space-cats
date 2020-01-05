package com.strzal.hungry.entity.makers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;


public class DrillButton extends BasicMakerButton {

    public DrillButton(HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.DRILL_COOKING_TIME;
    }

    @Override
    protected void loadTextures() {
        textureUp = assetManager.get(ImagesPaths.DRILL_01);
        textureDown = assetManager.get(ImagesPaths.DRILL_02);
        textureChecked = assetManager.get(ImagesPaths.DRILL_03);
    }

    @Override
    protected void buttonClicked() {
        if (canPressAgain) {
            canPressAgain = false;
            imageButton.setTouchable(Touchable.disabled);

            if (!isButtonPressed && gameController.isPossibleMakeMoreChips()) {
                isButtonPressed = !isButtonPressed;
                System.out.println("Drill Clicked");
                initialClickTime = TimeUtils.millis();
                gameController.useEnergy(GameSetting.DRILL_USED_ENERGY);
            }
        }
    }

    @Override
    protected void update() {
        if (isButtonPressed) {
            if (checkCookingTimeHasPassed()) {
                isButtonPressed = false;
                canPressAgain = true;
                gameController.addChip();
                imageButton.setTouchable(Touchable.enabled);
                imageButton.setChecked(false);
            } else {
                drawProgressBar();
            }
        }
        // always set disabled if cannot click
        else if (gameController.isPossibleMakeMoreChips()) {
            imageButton.setTouchable(Touchable.enabled);
        } else {
            imageButton.setTouchable(Touchable.disabled);
        }
    }

}
