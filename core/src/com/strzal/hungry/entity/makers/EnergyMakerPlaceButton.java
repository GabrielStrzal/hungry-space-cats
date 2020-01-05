package com.strzal.hungry.entity.makers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class EnergyMakerPlaceButton extends BasicMakerButton {


    public EnergyMakerPlaceButton(HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.ENERGY_MAKER_PLACE_TIME;

    }

    @Override
    protected void loadTextures() {
        textureUp = assetManager.get(ImagesPaths.ENERGY_MAKER_PLACE_01);
        textureDown = assetManager.get(ImagesPaths.ENERGY_MAKER_PLACE_02);
        textureChecked = assetManager.get(ImagesPaths.ENERGY_MAKER_PLACE_03);
    }

    @Override
    protected void buttonClicked() {
        if (canPressAgain) {
            canPressAgain = false;
            imageButton.setTouchable(Touchable.disabled);

            if (!isButtonPressed && game.getGameStats().useCash(GameSetting.ENERGY_MAKER_CASH_COST)) {
                isButtonPressed = !isButtonPressed;
                System.out.println("Energy Made");
                initialClickTime = TimeUtils.millis();

            }
        }
    }

    @Override
    protected void update() {
        if (isButtonPressed) {
            if (checkCookingTimeHasPassed()) {
                isButtonPressed = false;
                canPressAgain = true;

                gameController.addEnergy(GameSetting.ENERGY_MAKER_ADDED_ENERGY);

                imageButton.setTouchable(Touchable.enabled);
                imageButton.setChecked(false);
            } else {
                drawProgressBar(30, 8, 10, 3);
            }
        }
        // always set disabled if cannot click
        else if (game.getGameStats().getCash() > GameSetting.ENERGY_MAKER_CASH_COST) {
            imageButton.setTouchable(Touchable.enabled);
        } else {
            imageButton.setTouchable(Touchable.disabled);
        }
    }


}
