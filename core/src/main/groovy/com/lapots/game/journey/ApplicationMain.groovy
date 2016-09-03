package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.platform.ResourcePlatform;
import com.lapots.game.journey.platform.resource.ResourceRouter
import com.lapots.game.journey.util.GrlUtils;

class ApplicationMain extends Game {

    @Override
    public void create() {
        VisUI.load(SkinScale.X1)
        setScreen(new ApplicationMenuScreen())

        // come up with better idea
        ResourcePlatform.resources >>
                GrlUtils.createPostRequest("redis://123", "456", null)

        def result = ResourcePlatform.resources <<
                GrlUtils.createGetRequest("redis://123", [ "expected type" : "java.lang.String"] )

        println "Read from redis: $result"
    }

    @Override
    public void dispose() { VisUI.dispose() }
}
