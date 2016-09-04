package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.core.framework.life.LifeFramework
import com.lapots.game.journey.platform.ResourcePlatform;
import com.lapots.game.journey.platform.resource.ResourceRouter
import com.lapots.game.journey.util.GrlUtils;

class ApplicationMain extends Game {

    def life = new LifeFramework()
    @Override
    public void create() {
         VisUI.load(SkinScale.X1)
         try {
             setScreen(new ApplicationMenuScreen())
             life.initSubsystems()

             // come up with better idea
             ResourcePlatform >>
                     GrlUtils.createPostNoHeadersRequest("redis://123", "456")

             def result = ResourcePlatform <<
                     GrlUtils.createGetRequest("redis://123", [ "expected type" : "java.lang.String"] )

             println "Read from redis: $result"
         } catch (Exception e) {
             // might need some time before stopping whole application
             life.destroySubsystems()
         }
    }

    @Override
    public void dispose() {
        life.destroySubsystems()
        VisUI.dispose()
    }
}
