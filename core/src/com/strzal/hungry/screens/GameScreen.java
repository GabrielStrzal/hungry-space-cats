package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import com.strzal.hungry.controller.LevelOrderListLoaderController;
import com.strzal.hungry.controller.TimeController;
import com.strzal.hungry.entity.PumpEntity;
import com.strzal.hungry.entity.WaterEntity;
import com.strzal.hungry.hud.Hud;
import com.strzal.hungry.screenManager.ScreenEnum;

public class GameScreen extends BasicMenuScreen {

    GameController gameController;
    Hud hud;
    TimeController timeController;
    PumpEntity pumpEntity;

    LevelOrderListLoaderController levelOrderListLoaderController;

    public GameScreen(BasicGame game, int level) {
        super(game);
        levelOrderListLoaderController = new LevelOrderListLoaderController(game, stage);
        gameController = new GameController(100, levelOrderListLoaderController.getLevelList(level));
        timeController = new TimeController();
    }


    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        hud = new Hud(game, gameController);

        //Background
        Image background = new Image((Texture) assetManager.get(ImagesPaths.GAME_BACKGROUND));
        stage.addActor(background);

        int pumpEntityXPosition = 10;
        int pumpEntityYPosition = 90;
        pumpEntity = new PumpEntity(game, gameController, stage, pumpEntityXPosition, pumpEntityYPosition);

    }


    @Override
    public void render(float delta) {
        super.render(delta);


        pumpEntity.render();

        update();

        hud.draw();
        gameController.render(stage.getBatch());

        checkGameOver();
        checkLevelCompleted();

    }

    private void update() {

        updateOxygen();
        updateWater();

    }

    private void updateWater() {
        int waterYPosition = 90;

        //still room for more water
        if(gameController.getWaterEntityList().size() < gameController.getWater()){
            for (int i = 1; i <= gameController.getWater() - gameController.getWaterEntityList().size(); i++) {
                if(gameController.isPositionOneEmpty()){
                    //in position one
                    createWater(waterYPosition, 1); // 205
                } else {
                    //in position two
                    createWater(waterYPosition - 45, 2); // 160 (-45)
                }
            }
        }
    }




    private void createWater(int yPosition, int arrayPosition) {
        int waterEntityXPosition = 125;
        WaterEntity waterEntity = new WaterEntity(game, gameController, stage, waterEntityXPosition, yPosition, arrayPosition);
    }

    private void checkGameOver() {
        if(gameController.getOxygen() <= 0 || gameController.getEnergy() <= 0){
            ScreenManager.getInstance().showScreen(
                    ScreenEnum.MENU_SCREEN, game
            );
        }
    }

    private void checkLevelCompleted() {
        if(gameController.isCurrentLevelCompleted()){
            ScreenManager.getInstance().showScreen(
                    ScreenEnum.MENU_SCREEN, game
            );
        }
    }


    private void updateOxygen() {
        int initialTime = 100;
        gameController.setOxygen(initialTime - timeController.getTimePassedInSeconds());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        hud.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        hud.dispose();
    }
}
