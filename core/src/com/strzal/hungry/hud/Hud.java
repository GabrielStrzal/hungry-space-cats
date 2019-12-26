package com.strzal.hungry.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.strzal.gdx.BasicGame;
import com.strzal.hungry.config.GameConfig;
import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.controller.GameController;

public class Hud implements Disposable {

    private AssetManager assetManager;
    private Stage stage;
    private BasicGame game;
    private GameController gameController;

    private TextureAtlas atlas;
    private Skin skin;

    //Rectangles
    private ShapeRenderer shapeRenderer;


    //Labels
    Label cashLabel;

    //Constants
    private static float LABELS_Y_POSITION = GameConfig.SCREEN_HEIGHT - 40;
    private static float CASH_LABEL_X_POSITION = 30;
    private static float OXYGEN_LABEL_X_POSITION = 340;
    private static float ENERGY_LABEL_X_POSITION = 580;


    public Hud(BasicGame game, GameController gameController){
        this.game = game;
        this.gameController = gameController;
        stage = new Stage(new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        assetManager = game.getAssetManager();
        shapeRenderer = new ShapeRenderer();

        atlas = new TextureAtlas(ImagesPaths.UI_SKIN_ATLAS);
        skin = new Skin(Gdx.files.internal(ImagesPaths.UI_SKIN_JSON), atlas);

        addCashText();
        addOxygenText();
        addEnergyText();
    }

    private void addCashText() {
        cashLabel = new Label("Cash: " + gameController.getCash(), skin);
        cashLabel.setPosition(CASH_LABEL_X_POSITION, LABELS_Y_POSITION);
        stage.addActor(cashLabel);
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

    public void resize(int width, int height){
        stage.getViewport().update(width, height);
    }

    public void draw(){
        stage.act(Gdx.graphics.getDeltaTime());
        cashLabel.setText("Cash: " + gameController.getCash());
        stage.draw();

        drawEnergyBar();
        drawOxygenBar();

    }

    private void drawOxygenBar() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                OXYGEN_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                100 ,15);

        shapeRenderer.setColor(Color.SALMON);
        shapeRenderer.rect(
                OXYGEN_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                gameController.getOxygen(),15);
        shapeRenderer.end();
    }


    private void drawEnergyBar() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(
                ENERGY_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                100,15);

        shapeRenderer.setColor(Color.SALMON);
        shapeRenderer.rect(
                ENERGY_LABEL_X_POSITION + 70,
                LABELS_Y_POSITION + 6,
                gameController.getEnergy(),15);
        shapeRenderer.end();
    }


    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
