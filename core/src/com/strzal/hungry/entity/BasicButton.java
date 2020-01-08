package com.strzal.hungry.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.TimeUtils;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.controller.GameController;

public class BasicButton {

    protected HungrySpaceCats game;
    protected GameController gameController;
    protected Stage stage;
    protected AssetManager assetManager;

    //Rectangles
    private ShapeRenderer shapeRenderer;

    protected int xPosition;
    protected int yPosition;

    protected ImageButton imageButton;

    //timer
    protected long initialClickTime;
    protected long cookingTime = 10; //seconds

    public BasicButton(HungrySpaceCats game, final GameController gameController, Stage stage, int xPosition, int yPosition){
        this.game = game;
        this.gameController = gameController;
        this.stage = stage;
        this.assetManager = game.getAssetManager();

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        shapeRenderer = game.getShapeRenderer();

    }

    public void render(){

    }

    protected boolean checkCookingTimeHasPassed(){
        if( getTimePassedInSeconds() > cookingTime){
            game.getAudioHandler().playHappySound();
            System.out.println("Production Finished - Time");
            return true;
        }else{
            return false;
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    protected void drawProgressBar(){
        int barSize = 50;
        int barHeight = 10;
        int xTabSpace = 0;
        int yTabSpace = 3;
        drawProgressBar(barSize, barHeight, xTabSpace, yTabSpace);
    }

    protected void drawProgressBar(int barSize, int barHeight, int xTabSpace , int yTabSpace){

        float barX = xPosition + (imageButton.getWidth()/2) - (barSize/2) + xTabSpace;
        float barY = yPosition + imageButton.getHeight() + yTabSpace;


        //cookingTime = 100;
        //getTimePassedInSeconds() = progressionValue
        long progressionValue = (barSize * getTimePassedInSeconds())/cookingTime;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                barX,
                barY,
                barSize, barHeight);

        shapeRenderer.setColor(Color.SALMON);
        shapeRenderer.rect(
                barX,
                barY,
                barSize - progressionValue, barHeight);
        shapeRenderer.end();


    }

    private long getTimePassedInSeconds(){
        long currentTime = TimeUtils.millis();
        return (currentTime - initialClickTime)/1000;
    }
}
