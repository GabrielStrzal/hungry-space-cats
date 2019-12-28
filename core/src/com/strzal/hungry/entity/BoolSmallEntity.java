package com.strzal.hungry.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import lombok.Getter;

public class BoolSmallEntity extends BasicButton {

    private ImageButton boolSmallButton;
    @Getter
    private int arrayPosition;


    public BoolSmallEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        this.arrayPosition = arrayPosition;

        gameController.getBoolSmallEntityList().add(this);

        boolSmallButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_02)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_02_PRESSED))
        );


        boolSmallButton.setPosition(xPosition, yPosition);


        stage.addActor(boolSmallButton);
    }

    public int boolUsed() {
        //if(gameController.useChips()){
            gameController.getBoolSmallEntityList().remove(this);
            boolSmallButton.remove();
            return arrayPosition;
        //};
    }
}
