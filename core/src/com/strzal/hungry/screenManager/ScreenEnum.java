package com.strzal.hungry.screenManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.strzal.gdxUtilLib.loading.LoadingPaths;
import com.strzal.gdxUtilLib.screenManager.ScreenEnumInterface;
import com.strzal.gdxUtilLib.screens.LoadingScreen;
import com.strzal.hungry.HungrySpaceCats;
import com.strzal.hungry.screens.*;


/**
 * Based on http://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/
 */

public enum ScreenEnum implements ScreenEnumInterface {
    GAME_SCREEN {
        public Screen getScreen(Object... params) {
            return new GameScreen((HungrySpaceCats)params[0], (Boolean) params[1]);
        }
    },
    TEXT_SCREEN {
        public Screen getScreen(Object... params) {
            return new TextScreen((HungrySpaceCats)params[0], (String) params[1], (GameModeEnum) params[2]);
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
    },
    TUTORIAL_SCREEN {
        public Screen getScreen(Object... params) {
            return new TutorialScreen((HungrySpaceCats)params[0]);
        }
    };

}
