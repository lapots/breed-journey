package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.core.framework.life.LifeFramework
import com.lapots.game.journey.core.platform.CorePlatform
import com.lapots.game.journey.core.platform.ResourcePlatform
import com.lapots.game.journey.ims.domain.GRLProtocol
import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL;
import com.lapots.game.journey.util.GrlUtils
import kotlin.Pair;

class ApplicationMain extends Game {

    {
        // move to external module
        Map.metaClass.bitwiseNegate = {
            def entry0 = delegate.entrySet()[0]
            new kotlin.Pair(entry0.key, entry0.value)
        }
    }

    @Override
    public void create() {
        def grlMessage = new GRLMessageDSL()
        // TODO: create delegating wrapper and kotlin coerce support
        grlMessage.dsl {
            grlMessage.method { GRLProtocol.GRLMethod.POST }
            // cannot cast to final class Pair so this is the workaround
            grlMessage.header {  ~["contentType" : "object"] }
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
