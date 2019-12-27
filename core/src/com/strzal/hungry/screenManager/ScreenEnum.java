package com.strzal.hungry.screenManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.strzal.gdx.loading.LoadingPaths;
import com.strzal.gdx.screenManager.ScreenEnumInterface;
import com.strzal.gdx.screens.LoadingScreen;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.screens.GameScreen;
import com.strzal.hungry.screens.MenuScreen;


/**
 * Based on http://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/
 */

public enum ScreenEnum implements ScreenEnumInterface {
    GAME_SCREEN {
        public Screen getScreen(Object... params) {
            return new GameScreen((HungrySpaceCats)params[0], (Integer) params[1]);
        }
    },
    LOADING_SCREEN {
        public Screen getScreen(Object... params) {
            return new LoadingScreen((HungrySpaceCats)params[0], (LoadingPaths)params[1], (ScreenAdapter)params[2]);
        }
    },
    MENU_SCREEN {
        public Screen getScreen(Object... params) {
            return new MenuScreen((HungrySpaceCats)params[0]);
        }
    };

}
