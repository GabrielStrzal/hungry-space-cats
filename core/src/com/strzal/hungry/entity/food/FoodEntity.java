package com.strzal.hungry.entity.food;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.controller.GameController;
import com.strzal.hungry.entity.BasicButton;

public class FoodEntity extends BasicButton {

    protected ImageButton imageButton;
    protected int arrayPosition;

    protected Texture texture;
    protected Texture textureUp;


    public FoodEntity(HungrySpaceCats game, GameController gameController, Stage stage, int xPosition, int yPosition, int arrayPosition) {
        super(game, gameController, stage, xPosition, yPosition);
        this.arrayPosition = arrayPosition;
        loadTextures();
        imageButton = new ImageButton(
                new TextureRegionDrawable(texture),
                new TextureRegionDrawable(textureUp)
        );
        imageButton.setPosition(xPosition, yPosition);
        stage.addActor(imageButton);
    }

    protected void loadTextures() {
    }


    public int getArrayPosition() {
        return arrayPosition;
    }
}
