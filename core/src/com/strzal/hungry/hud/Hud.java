package com.strzal.hungry.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.config.GameConfig;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;
import com.strzal.hungry.handler.LevelStats;
import com.strzal.hungry.screenManager.ScreenEnum;

public class Hud {

    private AssetManager assetManager;
    private Stage stage;
    private HungrySpaceCats game;
    private GameController gameController;

    private TextureAtlas atlas;
    private Skin skin;

    //Rectangles
    private ShapeRenderer shapeRenderer;


    //Labels
    Label cashLabel;
    Label waveLabel;
    Label numberOfCatsInLineLabel;

    //Constants
    private static float LABELS_Y_POSITION = GameConfig.SCREEN_HEIGHT - 40;
    private static float CASH_LABEL_X_POSITION = 30;
    private static float WAVE_LABEL_X_POSITION = 195;
    private static float CATS_LEFT_LABEL_X_POSITION = 300;
    private static float OXYGEN_LABEL_X_POSITION = 340;
    private static float ENERGY_LABEL_X_POSITION = 545;
    private static float ENDLESS_LABEL_X_POSITION = 20;

    private static float MENU_LABEL_X_POSITION = 740;


    public Hud(HungrySpaceCats game, GameController gameController) {
        this.game = game;
        this.gameController = gameController;
        stage = new Stage(new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        assetManager = game.getAssetManager();
        shapeRenderer = game.getShapeRenderer();
        shapeRenderer.setProjectionMatrix(stage.getViewport().getCamera().combined);

        atlas = new TextureAtlas(ImagesPaths.UI_SKIN_ATLAS);
        skin = new Skin(Gdx.files.internal(ImagesPaths.UI_SKIN_JSON), atlas);


        addCashText();
        addWaveText();
        addOxygenText();
        addEnergyText();
        addGoToMenuButton();
        addEndlessText();
        addNumberOfCatsLeftText();
    }

    private void addGoToMenuButton() {
        TextButton menuButton = new TextButton("Exit", skin);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getAudioHandler().playButtonSound();
                game.getGameStatsHandler().saveLevelData(
                        new LevelStats(1, game.getGameStats().getWave(), game.getGameStats().getCash(), false)
                );

                ScreenManager.getInstance().showScreen(
                        ScreenEnum.MENU_SCREEN, game
                );
            }
        });
        menuButton.setPosition(MENU_LABEL_X_POSITION, LABELS_Y_POSITION);
        stage.addActor(menuButton);
    }

    private void addCashText() {
        cashLabel = new Label("Cash: " + gameController.getGameStats().getCash(), skin);
        cashLabel.setPosition(CASH_LABEL_X_POSITION, LABELS_Y_POSITION);
        stage.addActor(cashLabel);
    }

    private void addWaveText() {
        waveLabel = new Label("Week: " + gameController.getGameStats().getWave(), skin);
        waveLabel.setPosition(WAVE_LABEL_X_POSITION, LABELS_Y_POSITION);
        stage.addActor(waveLabel);
    }

    private void addOxygenText() {
        Label textLabel = new Label("Oxygen: ", skin);
        textLabel.setPosition(OXYGEN_LABEL_X_POSITION, LABELS_Y_POSITION);
        stage.addActor(textLabel);
    }

    private void addEnergyText() {
        Label textLabel = new Label("Energy: ", skin);
        textLabel.setPosition(ENERGY_LABEL_X_POSITION, LABELS_Y_POSITION);
        stage.addActor(textLabel);
    }

    private void addEndlessText() {
        if (gameController.isEndless()) {
            Label textLabel = new Label("ENDLESS", skin);
            textLabel.setColor(Color.RED);
            textLabel.setPosition(ENDLESS_LABEL_X_POSITION, LABELS_Y_POSITION - 30);
            stage.addActor(textLabel);
        }
    }

    private void addNumberOfCatsLeftText() {
        numberOfCatsInLineLabel = new Label("Number of cats in line: " + gameController.getHungryEntityList().size(), skin);
        numberOfCatsInLineLabel.setPosition(CATS_LEFT_LABEL_X_POSITION, LABELS_Y_POSITION - 30);
        stage.addActor(numberOfCatsInLineLabel);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void draw() {
        stage.act(Gdx.graphics.getDeltaTime());
        cashLabel.setText("Cash: " + gameController.getGameStats().getCash());
        waveLabel.setText("Week: " + gameController.getGameStats().getWave());
        numberOfCatsInLineLabel.setText("Number of cats in line: " + gameController.getHungryEntityList().size());
        stage.draw();

        drawEnergyBar();
        drawOxygenBar();
        updateAlarmSound();

    }

    private void updateAlarmSound() {
        if (gameController.getOxygen() < 20 || gameController.getEnergy() < 20 && !gameController.isGameOver()) {
            game.getAudioHandler().playAlarmSound();
        } else {
            game.getAudioHandler().stopAlarmSound();
        }
    }

    private void drawOxygenBar() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                OXYGEN_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                100, 15);

        if (gameController.getOxygen() < 20) {
            shapeRenderer.setColor(Color.RED);
        } else {
            shapeRenderer.setColor(Color.SALMON);
        }
        shapeRenderer.rect(
                OXYGEN_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                gameController.getOxygen(), 15);
        shapeRenderer.end();
    }


    private void drawEnergyBar() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                ENERGY_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                100, 15);

        if (gameController.getEnergy() < 20) {
            shapeRenderer.setColor(Color.RED);
        } else {
            shapeRenderer.setColor(Color.SALMON);
        }
        shapeRenderer.rect(
                ENERGY_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                gameController.getEnergy(), 15);
        shapeRenderer.end();
    }


    public Stage getStage() {
        return stage;
    }
}
