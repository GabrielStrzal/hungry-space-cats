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

public class ChipEntity extends BasicButton {

    private ImageButton chipsButton;
    @Getter
    private int arrayPosition;


    public ChipEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        this.arrayPosition = arrayPosition;

        gameController.getChipEntityList().add(this);

        chipsButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.CHIPS)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.CHIPS_PRESSED))
        );


        chipsButton.setPosition(xPosition, yPosition);
        chipsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                buttonClicked();
            }
        });

        stage.addActor(chipsButton);
    }

    private void buttonClicked() {
        if(gameController.useChips()){
            gameController.getChipEntityList().remove(this);
            chipsButton.remove();
        };
    }
}
