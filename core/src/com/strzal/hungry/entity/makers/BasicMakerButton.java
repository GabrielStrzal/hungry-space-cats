package com.strzal.hungry.entity.makers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.controller.GameController;
import com.strzal.hungry.entity.BasicButton;


public class BasicMakerButton extends BasicButton {

    protected boolean isButtonPressed = false;
    protected boolean canPressAgain = true;

    protected Texture textureUp;
    protected Texture textureDown;
    protected Texture textureChecked;


    public BasicMakerButton(final HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition) {
        super(game, gameController, stage, xPosition, yPosition);

        loadTextures();

        imageButton = new ImageButton(new TextureRegionDrawable(textureUp),
                new TextureRegionDrawable(textureDown),
                new TextureRegionDrawable(textureChecked));

        imageButton.setPosition(xPosition, yPosition);
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.getAudioHandler().playButtonSound();
                buttonClicked();
            }
        });

        stage.addActor(imageButton);
    }

    protected void loadTextures() {
    }

    protected void buttonClicked() {
    }

    @Override
    public void render() {
        update();
    }


    protected void update() {

    }


}
