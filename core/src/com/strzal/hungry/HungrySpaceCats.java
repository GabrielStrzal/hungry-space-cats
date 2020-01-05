package com.strzal.hungry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.gdxUtilLib.screenManager.ScreenManager;
import com.strzal.hungry.audio.AudioHandler;
import com.strzal.hungry.config.GameConfig;
import com.strzal.hungry.config.GameStats;
import com.strzal.hungry.handler.GameStatsHandler;
import com.strzal.hungry.loading.LoadingPathsImpl;
import com.strzal.hungry.screenManager.ScreenEnum;
import com.strzal.hungry.screens.MenuScreen;

public class HungrySpaceCats extends BasicGame {
	private SpriteBatch batch;
	private GameStats gameStats;
	private AudioHandler audioHandler;
	private GameStatsHandler gameStatsHandler;
	private ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		screenWidth = GameConfig.SCREEN_WIDTH;
		screenHeight = GameConfig.SCREEN_HEIGHT;

		batch = new SpriteBatch();
		loadingPaths = new LoadingPathsImpl();
		audioHandler = new AudioHandler(this);
		gameStatsHandler = new GameStatsHandler();
		shapeRenderer = new ShapeRenderer();

		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance()
				.showScreen(ScreenEnum.LOADING_SCREEN, this, loadingPaths, new MenuScreen(this));
	}


	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public GameStats getGameStats() {
		return gameStats;
	}

	public AudioHandler getAudioHandler() {
		return audioHandler;
	}

	public GameStatsHandler getGameStatsHandler() {
		return gameStatsHandler;
	}

	public void setGameStats(GameStats gameStats) {
		this.gameStats = gameStats;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
}
