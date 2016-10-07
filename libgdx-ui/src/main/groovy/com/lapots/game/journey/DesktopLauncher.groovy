package com.lapots.game.journey;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import groovy.json.JsonSlurper;

public class DesktopLauncher {

    /**
     * Equals to
     *      // check that method exist -> then continue
     *      private static $classpathFileResource(String resource) {
     *          def classLoader = Thread.currentThread().getContextClassLoader()
     *          return new File(classLoader.getResource(resource).getFile())
     *      }
     *      // check that method exists -> then invoke
     *      private static $jsonFileResource(String resource) {
     *          return new groovy.json.JsonSlurper().parse($classpathFileResource(resource))
     *      }
     *
     *      def config = $jsonFileResource("application.config")
     */
    // @JsonResource("application.config")
    def config

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        def classLoader = Thread.currentThread().getContextClassLoader()
        def jsonConfig = new JsonSlurper().parse(new File(classLoader.getResource("application.config").getFile()))

        config.width = jsonConfig.window.width
        config.height = jsonConfig.window.height


        new LwjglApplication(new ApplicationMain(), config);
    }
}
