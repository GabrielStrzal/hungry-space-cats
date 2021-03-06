package com.strzal.hungry.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.hungry.config.GamePositions;
import com.strzal.hungry.config.GameSetting;
import com.strzal.hungry.config.GameTexts;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import com.strzal.hungry.controller.LevelOrderListLoaderController;
import com.strzal.hungry.controller.TimeController;
import com.strzal.hungry.entity.food.*;
import com.strzal.hungry.entity.makers.*;
import com.strzal.hungry.handler.LevelStats;
import com.strzal.hungry.hud.Hud;
import com.strzal.hungry.screenManager.ScreenEnum;

public class GameScreen extends BasicMenuScreen {

    private GameController gameController;
    private Hud hud;
    private TimeController timeController;
    private WaterPumpButton waterPumpButton;
    private DrillButton drillButton;
    private CookingPlaceButton cookingPlaceButton;
    private FishMakerButton fishMakerButton;
    private CookingFishPlaceButton cookingFishPlaceButton;
    private EnergyMakerPlaceButton energyMakerPlaceButton;
    private OxygenMakerPlaceButton oxygenMakerPlaceButton;

    private LevelOrderListLoaderController levelOrderListLoaderController;
    private int maxWaves;


    public GameScreen(BasicGame game, boolean isEndless) {
        super(game);
        levelOrderListLoaderController = new LevelOrderListLoaderController(this.game, stage);
        gameController = new GameController(levelOrderListLoaderController.getLevelList(), this,
                this.game.getGameStats(), this.game.getAudioHandler());
        timeController = new TimeController();
        gameController.setGameOver(false);
        gameController.setEndless(isEndless);
        setMaxWave();
    }

    private void setMaxWave() {
        if(gameController.isEndless()){
            maxWaves = GameSetting.MAXIMUM_ENDLESS_WAVE_IN_GAME_MODE;
        } else {
            maxWaves = GameSetting.MAXIMUM_WAVE_IN_GAME_MODE;
        }
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

        cookingPlaceButton = new CookingPlaceButton(game, gameController, stage,
                GamePositions.COOKING_PLACE_X_POSITION, GamePositions.COOKING_PLACE_Y_POSITION);

        fishMakerButton = new FishMakerButton(game, gameController, stage,
                GamePositions.FISH_MAKER_PLACE_X_POSITION, GamePositions.FISH_MAKER_PLACE_Y_POSITION);

        cookingFishPlaceButton = new CookingFishPlaceButton(game, gameController, stage,
                GamePositions.COOKING_FISH_PLACE_X_POSITION, GamePositions.COOKING_FISH_PLACE_Y_POSITION);

        energyMakerPlaceButton = new EnergyMakerPlaceButton(game, gameController, stage,
                GamePositions.ENERGY_MAKER_PLACE_X_POSITION, GamePositions.ENERGY_MAKER_PLACE_Y_POSITION);

        oxygenMakerPlaceButton = new OxygenMakerPlaceButton(game, gameController, stage,
                GamePositions.OXYGEN_MAKER_PLACE_X_POSITION, GamePositions.OXYGEN_MAKER_PLACE_Y_POSITION);


        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(hud.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);

    }


    @Override
    public void render(float delta) {
        super.render(delta);


        waterPumpButton.render();
        drillButton.render();
        cookingPlaceButton.render();
        fishMakerButton.render();
        cookingFishPlaceButton.render();
        energyMakerPlaceButton.render();
        oxygenMakerPlaceButton.render();

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
        updateBools();
        updateFish();
        updateSmallBools();

    }

    private void updateWater() {

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

    public ChipBoolEntity createChipBoolEntity(int yPosition, int arrayPosition) {
         return new ChipBoolEntity(game, gameController, stage,
                GamePositions.BOOL_X_POSITION, yPosition, arrayPosition);
    }

    public FishBoolEntity createFishBoolEntity(int yPosition, int arrayPosition) {
        return new FishBoolEntity(game, gameController, stage,
                GamePositions.BOOL_SMALL_X_POSITION, yPosition, arrayPosition);
    }

    private void updateChips() {

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

    private void updateFish() {
        //verificar se ja foi criado o peixe
        if( gameController.getCurrentFishEntity() == null && gameController.getFish() == 1){
            createFish();
        }
    }

    private void createFish() {
        new FishEntity(game, gameController, stage,
                GamePositions.FISH_X_POSITION, GamePositions.FISH_Y_POSITION);
    }

    private void updateBools() {

        if(gameController.getBoolEntityList().size() < gameController.getBools()){
            for (int i = 1; i <= gameController.getBools() - gameController.getBoolEntityList().size(); i++) {
                if(gameController.isBoolPositionOneEmpty()){
                    //in position one
                    createBools(GamePositions.BOOL_Y_POSITION, 1);
                } else {
                    //in position two
                    createBools(GamePositions.BOOL_Y_SECOND_POSITION, 2);
                }
            }
        }
    }

    private void createBools(int yPosition, int arrayPosition) {
        BoolEntity boolEntity = new BoolEntity(game, gameController, stage,
                GamePositions.BOOL_X_POSITION, yPosition, arrayPosition);
    }

    private void updateSmallBools() {

        if(gameController.getBoolSmallEntityList().size() < gameController.getSmallBools()){
            for (int i = 1; i <= gameController.getSmallBools() - gameController.getBoolSmallEntityList().size(); i++) {
                if(gameController.isBoolSmallPositionOneEmpty()){
                    //in position one
                    createSmallBools(GamePositions.BOOL_SMALL_Y_POSITION, 1);
                } else {
                    //in position two
                    createSmallBools(GamePositions.BOOL_SMALL_Y_SECOND_POSITION, 2);
                }
            }
        }
    }

    private void createSmallBools(int yPosition, int arrayPosition) {
        BoolSmallEntity boolSmallEntity = new BoolSmallEntity(game, gameController, stage,
                GamePositions.BOOL_SMALL_X_POSITION, yPosition, arrayPosition);
    }

    private void checkGameOver() {
        if(gameController.getOxygen() <= 0 || gameController.getEnergy() <= 0){
            game.getAudioHandler().stopAlarmSound();
            gameController.setGameOver(true);
            game.getGameStatsHandler().saveLevelData(
                    new LevelStats(1, game.getGameStats().getWave(),game.getGameStats().getCash(), false));
            ScreenManager.getInstance().showScreen(
                    ScreenEnum.TEXT_SCREEN, game, GameTexts.GAME_OVER_TEXT, GameModeEnum.GAME_OVER
            );
        }
    }

    //TODO verify
    private void checkLevelCompleted() {
        if(gameController.isCurrentLevelCompleted()){

            game.getGameStats().addWave();
            game.getAudioHandler().stopAlarmSound();
            gameController.setGameOver(true);

            //Level completed
            if(game.getGameStats().getWave() > maxWaves){
                //Game Completed (You WON)
                game.getGameStatsHandler().saveLevelData(
                        new LevelStats(1,game.getGameStats().getWave(),game.getGameStats().getCash(), true));
                ScreenManager.getInstance().showScreen(
                        ScreenEnum.TEXT_SCREEN, game, GameTexts.GAME_WON_TEXT, GameModeEnum.YOU_WON
                );
            } else{
                //Next wave
                if(gameController.isEndless()){
                    ScreenManager.getInstance().showScreen(
                            ScreenEnum.TEXT_SCREEN, game, GameTexts.WAVE_COMPLETE_TEXT, GameModeEnum.LEVEL_COMPLETED_ENDLESS
                    );
                } else {
                    ScreenManager.getInstance().showScreen(
                            ScreenEnum.TEXT_SCREEN, game, GameTexts.WAVE_COMPLETE_TEXT, GameModeEnum.LEVEL_COMPLETED
                    );
                }

            }
        }
    }


    private void updateOxygen() {

        long oxygen = gameController.getOxygenForMeter() - timeController.getTimePassedInSeconds();
        if(oxygen > 100){
            oxygen = 100;
        }
        gameController.setOxygen(oxygen);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        hud.resize(width, height);
    }

}
