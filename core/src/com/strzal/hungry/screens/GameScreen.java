package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.hungry.config.GamePositions;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import com.strzal.hungry.controller.LevelOrderListLoaderController;
import com.strzal.hungry.controller.TimeController;
import com.strzal.hungry.entity.ChipEntity;
import com.strzal.hungry.entity.DrillButton;
import com.strzal.hungry.entity.WaterPumpButton;
import com.strzal.hungry.entity.WaterEntity;
import com.strzal.hungry.hud.Hud;
import com.strzal.hungry.screenManager.ScreenEnum;

public class GameScreen extends BasicMenuScreen {

    private GameController gameController;
    private Hud hud;
    private TimeController timeController;
    private WaterPumpButton waterPumpButton;
    private DrillButton drillButton;

    private LevelOrderListLoaderController levelOrderListLoaderController;

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

        waterPumpButton = new WaterPumpButton(game, gameController, stage,
                GamePositions.WATER_DRILL_X_POSITION, GamePositions.WATER_DRILL_Y_POSITION);

        drillButton = new DrillButton(game, gameController, stage,
                GamePositions.DRILL_X_POSITION, GamePositions.DRILL_Y_POSITION);

    }


    @Override
    public void render(float delta) {
        super.render(delta);


        waterPumpButton.render();
        drillButton.render();

        update();

        hud.draw();
        gameController.render(stage.getBatch());

        checkGameOver();
        checkLevelCompleted();

    }

    private void update() {

        updateOxygen();
        updateWater();
        updateChips();

    }

    private void updateWater() {

        //still room for more water
        if(gameController.getWaterEntityList().size() < gameController.getWater()){
            for (int i = 1; i <= gameController.getWater() - gameController.getWaterEntityList().size(); i++) {
                if(gameController.isWaterPositionOneEmpty()){
                    //in position one
                    createWater(GamePositions.WATER_Y_POSITION, 1);
                } else {
                    //in position two
                    createWater(GamePositions.WATER_Y_SECOND_POSITION, 2);
                }
            }
        }
    }




    private void createWater(int yPosition, int arrayPosition) {
        WaterEntity waterEntity = new WaterEntity(game, gameController, stage,
                GamePositions.WATER_X_POSITION, yPosition, arrayPosition);
    }

    private void updateChips() {

        //still room for more water
        if(gameController.getChipEntityList().size() < gameController.getChips()){
            for (int i = 1; i <= gameController.getChips() - gameController.getChipEntityList().size(); i++) {
                if(gameController.isChipPositionOneEmpty()){
                    //in position one
                    createChips(GamePositions.CHIPS_Y_POSITION, 1);
                } else {
                    //in position two
                    createChips(GamePositions.CHIPS_Y_SECOND_POSITION, 2);
                }
            }
        }
    }

    private void createChips(int yPosition, int arrayPosition) {
        ChipEntity chipsEntity = new ChipEntity(game, gameController, stage,
                GamePositions.CHIPS_X_POSITION, yPosition, arrayPosition);
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
        gameController.setOxygen(GameSetting.OXYGEN_INITIAL_TIME - timeController.getTimePassedInSeconds());
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
