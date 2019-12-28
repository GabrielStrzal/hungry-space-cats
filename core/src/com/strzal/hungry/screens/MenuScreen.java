package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.hungry.config.GameStats;
import com.strzal.hungry.config.GameTexts;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.screenManager.ScreenEnum;

public class MenuScreen extends BasicMenuScreen {


    public MenuScreen(BasicGame game) {
        super(game);
    }


    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.bottom().padBottom(10);

        //Create buttons
        TextButton playButton = new TextButton("Play", skin);
        TextButton playEndlessButton = new TextButton("Play Endless Mode", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        Image background = new Image((Texture) game.getAssetManager().get(ImagesPaths.MENU_BACKGROUND));

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setGameStats(new GameStats());
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.TEXT_SCREEN, game, GameTexts.START_TEXT, GameMode.STORY_MODE
                );
            }
        });

        playEndlessButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setGameStats(new GameStats());
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.TEXT_SCREEN, game, GameTexts.ENDLESS_TEXT, GameMode.ENDLESS_MODE
                );
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(playEndlessButton);
        mainTable.row();
        mainTable.add(exitButton);

        stage.addActor(background);
        //Add table to stage
        stage.addActor(mainTable);
    }

}
