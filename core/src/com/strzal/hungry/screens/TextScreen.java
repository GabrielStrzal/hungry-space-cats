package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.screenManager.ScreenEnum;

public class TextScreen extends BasicMenuScreen {

    private String textToBeDisplayed;
    private int nextScreen;

    public TextScreen(BasicGame game, String textToBeDisplayed, int nextScreen) {
        super(game);
        this.textToBeDisplayed = textToBeDisplayed;
        this.nextScreen = nextScreen;
    }


    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);



        //Create buttons
        TextButton playButton = new TextButton("NEXT", skin);

        Image background = new Image((Texture) game.getAssetManager().get(ImagesPaths.GAME_TEXT_BACKGROUND));

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(nextScreen == 1){
                    ScreenManager.getInstance().showScreen( ScreenEnum.GAME_SCREEN, game, 1 );
                } else {
                    ScreenManager.getInstance().showScreen( ScreenEnum.MENU_SCREEN, game );
                }


            }
        });


        //Create Table
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        //
        mainTable.bottom().padBottom(10);

        //Add buttons to table
        mainTable.add(playButton);


        stage.addActor(background);
        //Add table to stage
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
        mainTable.left().top();
        mainTable.padLeft(90);
        mainTable.padTop(50);

        // Create a TypingLabel instance with your custom text
        label = new TypingLabel(textToBeDisplayed, skin);

        //Add buttons to table
        mainTable.add(label);
        mainTable.row();


        //Add table to stage
        stage.addActor(mainTable);
    }

}
