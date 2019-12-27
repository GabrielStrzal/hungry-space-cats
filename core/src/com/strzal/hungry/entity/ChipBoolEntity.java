package com.strzal.hungry.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import lombok.Getter;

public class ChipBoolEntity extends BasicButton {

    private ImageButton chipBoolButton;
    @Getter
    private int arrayPosition;


    public ChipBoolEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        this.arrayPosition = arrayPosition;

        gameController.getChipBoolEntityList().add(this);

        chipBoolButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_CHIPS)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_CHIPS_PRESSED))
        );


        chipBoolButton.setPosition(xPosition, yPosition);
        chipBoolButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        stage.addActor(chipBoolButton);
    }

    private void buttonClicked() {
        if(gameController.useChipBool()){
            gameController.getChipBoolEntityList().remove(this);
            chipBoolButton.remove();
        };
    }
}
