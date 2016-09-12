package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.core.framework.life.LifeFramework
import com.lapots.game.journey.core.platform.CorePlatform
import com.lapots.game.journey.core.platform.ResourcePlatform;
import com.lapots.game.journey.util.GrlUtils;

class ApplicationMain extends Game {

    @Override
    public void create() {
        addShutdownHook {
            CorePlatform.managed["lifeFramework"].destroySubsystems()
        }

         VisUI.load(SkinScale.X1)
         try {
             setScreen(new ApplicationMenuScreen())
             CorePlatform.managed["lifeFramework"].initSubsystems()

             // come up with better idea
             ResourcePlatform >>
                     GrlUtils.createPostNoHeadersRequest("redis://123", "456")

             def result = ResourcePlatform <<
                     GrlUtils.createGetRequest("redis://123", [ "expected type" : "java.lang.String"] )

             println "Read from redis: $result"
         } catch (Exception e) {
              e.printStackTrace()
         }
    }

    @Override
    public void dispose() {
        VisUI.dispose()
    }
}
