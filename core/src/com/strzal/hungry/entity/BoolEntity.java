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

public class BoolEntity extends BasicButton {

    private ImageButton boolButton;
    @Getter
    private int arrayPosition;


    public BoolEntity(BasicGame game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        this.arrayPosition = arrayPosition;

        gameController.getBoolEntityList().add(this);

        boolButton = new ImageButton(
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_01)),
                new TextureRegionDrawable((Texture) assetManager.get(ImagesPaths.BOOL_01_PRESSED))
        );


        boolButton.setPosition(xPosition, yPosition);


        stage.addActor(boolButton);
    }
}
