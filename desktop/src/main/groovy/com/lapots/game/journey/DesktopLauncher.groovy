package com.lapots.game.journey;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lapots.game.journey.ApplicationMain;

public class DesktopLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1000;
        config.height = 800;

        new LwjglApplication(new ApplicationMain(), config);

        addShutdownHook {
            // add redis stop
            println "do something with it"
        }
    }
}
