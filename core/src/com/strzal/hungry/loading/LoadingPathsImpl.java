package com.strzal.hungry.loading;

import com.strzal.hungry.constants.ImagesPaths;
import com.strzal.hungry.constants.SoundPaths;

import java.util.ArrayList;
import java.util.List;

public class LoadingPathsImpl implements LoadingPaths {
    @Override
    public List<String> getTexturePaths() {
        List<String> list = new ArrayList<>();

        //Menu
        list.add(ImagesPaths.MENU_BACKGROUND);
        list.add(ImagesPaths.GAME_BACKGROUND);
        list.add(ImagesPaths.GAME_TEXT_BACKGROUND);

        //Buttons
        list.add(ImagesPaths.TRANSPARENT);
        list.add(ImagesPaths.WATTER_DRILL_01);
        list.add(ImagesPaths.WATTER_DRILL_02);
        list.add(ImagesPaths.WATTER_DRILL_03);

        list.add(ImagesPaths.DRILL_01);
        list.add(ImagesPaths.DRILL_02);
        list.add(ImagesPaths.DRILL_03);

        list.add(ImagesPaths.COOKING_PLACE_01);
        list.add(ImagesPaths.COOKING_PLACE_02);
        list.add(ImagesPaths.COOKING_PLACE_03);

        list.add(ImagesPaths.COOKING_SMALL_PLACE_01);
        list.add(ImagesPaths.COOKING_SMALL_PLACE_02);
        list.add(ImagesPaths.COOKING_SMALL_PLACE_03);

        list.add(ImagesPaths.COOKING_FISH_PLACE_01);
        list.add(ImagesPaths.COOKING_FISH_PLACE_02);
        list.add(ImagesPaths.COOKING_FISH_PLACE_03);

        list.add(ImagesPaths.ENERGY_MAKER_PLACE_01);
        list.add(ImagesPaths.ENERGY_MAKER_PLACE_02);
        list.add(ImagesPaths.ENERGY_MAKER_PLACE_03);

        list.add(ImagesPaths.OXYGEN_MAKER_PLACE_01);
        list.add(ImagesPaths.OXYGEN_MAKER_PLACE_02);
        list.add(ImagesPaths.OXYGEN_MAKER_PLACE_03);

        //Foods
        list.add(ImagesPaths.WATTER);
        list.add(ImagesPaths.WATTER_PRESSED);

        list.add(ImagesPaths.CHIPS);
        list.add(ImagesPaths.CHIPS_PRESSED);

        list.add(ImagesPaths.FISH);
        list.add(ImagesPaths.FISH_PRESSED);

        list.add(ImagesPaths.BOOL_01);
        list.add(ImagesPaths.BOOL_01_PRESSED);
        list.add(ImagesPaths.BOOL_02);
        list.add(ImagesPaths.BOOL_02_PRESSED);

        list.add(ImagesPaths.BOOL_CHIPS);
        list.add(ImagesPaths.BOOL_CHIPS_PRESSED);

        list.add(ImagesPaths.BOOL_FISH);
        list.add(ImagesPaths.BOOL_FISH_PRESSED);

        //Hungry
        list.add(ImagesPaths.HUNGRY_CAT);
        list.add(ImagesPaths.HUNGRY_CAT_2);
        list.add(ImagesPaths.HUNGRY_CAT_3);

        list.add(ImagesPaths.HUNGRY_WATTER);
        list.add(ImagesPaths.HUNGRY_BOOL_CHIPS);
        list.add(ImagesPaths.HUNGRY_BOOL_FISH);


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
        List<String> list = new ArrayList<>();

        //Menu
        list.add(SoundPaths.CORRECT_AUDIO);
        list.add(SoundPaths.WRONG_AUDIO);
        list.add(SoundPaths.MENU_BUTTON_AUDIO);
        list.add(SoundPaths.KITTEN_MEOW_AUDIO);
        list.add(SoundPaths.ORDER_COMPLETE_AUDIO);
        list.add(SoundPaths.ALARM_AUDIO);

        return list;
    }

}
