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
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.screenManager.ScreenEnum;

public class GameScreen extends BasicMenuScreen {


    public GameScreen(BasicGame game) {
        super(game);
    }


    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();


        Image background = new Image((Texture) game.getAssetManager().get(ImagesPaths.GAME_BACKGROUD));
        TextButton quitCurrentQuizListButton = new TextButton("Quit Current Quiz", skin);

        //Add listeners to buttons
//        choice1Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                validate(questionListIndexShuffler.get(0));
//            }
//        });

        quitCurrentQuizListButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               ScreenManager.getInstance().showScreen(ScreenEnum.MENU_SCREEN, game);
            }
        });

        //Add buttons to table



        mainTable.row();
        mainTable.add(quitCurrentQuizListButton);

        stage.addActor(background);
        //Add table to stage
        stage.addActor(mainTable);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
