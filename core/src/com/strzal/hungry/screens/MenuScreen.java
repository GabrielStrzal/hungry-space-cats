package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.strzal.hungry.BasicGame;
import com.strzal.hungry.config.GameStats;
import com.strzal.hungry.config.GameTexts;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.screenManager.ScreenEnum;
import com.strzal.hungry.screenManager.ScreenManager;

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
        TextButton gameStatsButton = new TextButton("Game Stats", skin);

        Image background = new Image((Texture) game.getAssetManager().get(ImagesPaths.MENU_BACKGROUND));

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getAudioHandler().playButtonSound();
                game.setGameStats(new GameStats());
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.TEXT_SCREEN, game, GameTexts.START_TEXT, GameModeEnum.STORY_MODE
                );
            }
        });

        // Only unlock if game finished
        if(game.getGameStatsHandler().getSavedData().isGameWon()) {
            playEndlessButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.getAudioHandler().playButtonSound();
                    game.setGameStats(new GameStats());
                    ScreenManager.getInstance().showScreen(
                            ScreenEnum.TEXT_SCREEN, game, GameTexts.ENDLESS_TEXT, GameModeEnum.ENDLESS_MODE
                    );
                }
            });
        } else {
            playEndlessButton.setColor(Color.GRAY);
        }

        gameStatsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getAudioHandler().playButtonSound();
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.TEXT_SCREEN, game, GameTexts.GAME_STATS_TEXT, GameModeEnum.GAME_STATS
                );
            }
        });

        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(playEndlessButton);
        mainTable.row();
        mainTable.add(gameStatsButton);

        stage.addActor(background);
        //Add table to stage
        stage.addActor(mainTable);
    }

}
