package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.hungry.config.GameStats;
import com.strzal.hungry.config.GameTexts;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.screenManager.ScreenEnum;

public class TutorialScreen extends BasicMenuScreen {


    public TutorialScreen(BasicGame game) {
        super(game);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.bottom().padBottom(10);

        TextButton nextButton = new TextButton("Menu", skin);

        Image background = new Image((Texture) game.getAssetManager().get(ImagesPaths.GAME_TUTORIAL_BACKGROUND));

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getAudioHandler().playButtonSound();
                game.setGameStats(new GameStats());
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.MENU_SCREEN, game
                );
            }
        });



        mainTable.add(nextButton);

        stage.addActor(background);
        stage.addActor(mainTable);
        initText();
    }

    private void initText(){
        //labels and text
        TypingLabel label;

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.left().bottom();
        mainTable.padLeft(340);
        mainTable.padBottom(160);

        // Create a TypingLabel instance with your custom text
        label = new TypingLabel(GameTexts.TUTORIAL_TEXT, skin);


        //Add buttons to table
        mainTable.add(label);
        mainTable.row();


        //Add table to stage
        stage.addActor(mainTable);
    }

}
