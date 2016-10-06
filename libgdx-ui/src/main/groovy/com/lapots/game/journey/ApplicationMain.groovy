package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lapots.game.journey.ast.managed.Managed

class ApplicationMain extends Game {

    // @Managed("lifeFramework")
    def lifeFramework

    @Override
    public void create() {
        VisUI.load(SkinScale.X1)
        try {
            setScreen(new ApplicationMenuScreen())
            // lifeFramework.initSubsystems()
            /* provide some parameters like [--withIndex]
            // come up with better idea
            ResourcePlatform >>
                    GrlUtils.createPostNoHeadersRequest("redis://123", "456")

            def result = ResourcePlatform <<
                    GrlUtils.createGetRequest("redis://123", [ "expected type" : "java.lang.String"] )

            println "Read from redis: $result"
            */
        } catch (Exception e) {
             e.printStackTrace()
        }
    }

    @Override
    public void dispose() {
        VisUI.dispose()
    }
}
