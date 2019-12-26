package com.strzal.hungry.loading;

import com.strzal.gdx.loading.LoadingPaths;
import com.strzal.hungry.constants.ImagesPaths;

import java.util.ArrayList;
import java.util.List;

public class LoadingPathsImpl implements LoadingPaths {
    @Override
    public List<String> getTexturePaths() {
        List<String> list = new ArrayList<>();

        //Menu
        list.add(ImagesPaths.BADLOGIC);
        list.add(ImagesPaths.MENU_BACKGROUD);
        list.add(ImagesPaths.GAME_BACKGROUD);

        //Buttons
        list.add(ImagesPaths.TRANSPARENT);
        list.add(ImagesPaths.WATTER_DRILL_01);
        list.add(ImagesPaths.WATTER_DRILL_02);
        list.add(ImagesPaths.WATTER_DRILL_03);

        list.add(ImagesPaths.WATTER);
        list.add(ImagesPaths.WATTER_PRESSED);


        return list;
    }

    @Override
    public List<String> getBitmapPaths() {
        List<String> list = new ArrayList<>();
        //list.add(GameAssets.GAME_FONT);
        return list;
    }

    @Override
    public List<String> getTileMapPaths() {
        List<String> list = new ArrayList<>();

        //Levels
        //list.add(LevelNames.LEVEL_1);


        return list;
    }

    @Override
    public List<String> getMusicPaths() {
        return null;
    }

    @Override
    public List<String> getSoundPaths() {
        return null;
    }

}
