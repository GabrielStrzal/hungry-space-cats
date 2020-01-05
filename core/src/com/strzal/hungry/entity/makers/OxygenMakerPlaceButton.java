package com.strzal.hungry.entity.makers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class OxygenMakerPlaceButton extends BasicMakerButton {


    public OxygenMakerPlaceButton(HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        cookingTime = GameSetting.OXYGEN_MAKER_PLACE_TIME;

    }

    @Override
    protected void loadTextures() {
        textureUp = assetManager.get(ImagesPaths.OXYGEN_MAKER_PLACE_01);
        textureDown = assetManager.get(ImagesPaths.OXYGEN_MAKER_PLACE_02);
        textureChecked = assetManager.get(ImagesPaths.OXYGEN_MAKER_PLACE_03);
    }

    @Override
    protected void buttonClicked() {
        if (canPressAgain) {
            canPressAgain = false;
            imageButton.setTouchable(Touchable.disabled);

            if (!isButtonPressed && game.getGameStats().useCash(GameSetting.OXYGEN_MAKER_CASH_COST)) {
                isButtonPressed = !isButtonPressed;
                System.out.println("Oxygen Made");
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

                gameController.addOxygen(GameSetting.OXYGEN_MAKER_ADDED_OXYGEN);

                imageButton.setTouchable(Touchable.enabled);
                imageButton.setChecked(false);
            } else {
                drawProgressBar(30, 8, 10, 3);
            }
        }
        // always set disabled if cannot click
        else if (game.getGameStats().getCash() > GameSetting.OXYGEN_MAKER_CASH_COST) {
            imageButton.setTouchable(Touchable.enabled);
        } else {
            imageButton.setTouchable(Touchable.disabled);
        }
    }


}
