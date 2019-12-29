package com.strzal.hungry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strzal.gdx.BasicGame;
import com.strzal.gdx.screenManager.ScreenManager;
import com.strzal.hungry.audio.AudioHandler;
import com.strzal.hungry.config.GameConfig;
import com.strzal.hungry.config.GameStats;
import com.strzal.hungry.handler.GameStatsHandler;
import com.strzal.hungry.loading.LoadingPathsImpl;
import com.strzal.hungry.screenManager.ScreenEnum;
import com.strzal.hungry.screens.MenuScreen;
import lombok.Getter;
import lombok.Setter;

@Getter
public class HungrySpaceCats extends BasicGame {
	private SpriteBatch batch;
	@Setter
	private GameStats gameStats;
	private AudioHandler audioHandler;
	private GameStatsHandler gameStatsHandler;

	@Override
	public void create () {
		screenWidth = GameConfig.SCREEN_WIDTH;
		screenHeight = GameConfig.SCREEN_HEIGHT;

		batch = new SpriteBatch();
		loadingPaths = new LoadingPathsImpl();
		audioHandler = new AudioHandler(this);
		gameStatsHandler = new GameStatsHandler();

		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance()
				.showScreen(ScreenEnum.LOADING_SCREEN, this, loadingPaths, new MenuScreen(this));
	}


	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
