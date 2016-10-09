package com.lapots.game.journey;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.lapots.game.journey.ui.helper.UiHelper

public class DesktopLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = UiHelper["window.width"] as int
        config.height = UiHelper["window.height"] as int

        new LwjglApplication(new ApplicationMain(), config);
    }

}
