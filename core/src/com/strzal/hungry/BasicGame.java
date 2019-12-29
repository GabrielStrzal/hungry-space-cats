package com.strzal.hungry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strzal.hungry.loading.LoadingPaths;

public abstract class BasicGame extends Game {

    public static final float PPM = 100;

    protected float screenWidth;
    protected float screenHeight;


    public SpriteBatch batch;
    protected final AssetManager assetManager = new AssetManager();
    protected LoadingPaths loadingPaths;

    public int currentLevel;

    public static float getPPM() {
        return PPM;
    }

    public float getScreenWidth() {
        return screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public LoadingPaths getLoadingPaths() {
        return loadingPaths;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
