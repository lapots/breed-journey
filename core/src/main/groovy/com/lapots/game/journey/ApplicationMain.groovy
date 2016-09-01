package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.platform.core.protocol.GRLMessage
import com.lapots.game.journey.platform.resource.ResourceRouter
import com.lapots.game.journey.util.GrlUtils;

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

@Component
class ApplicationMain extends Game {

    @Autowired
    ApplicationMenuScreen screen

    @Override
    public void create() {
        VisUI.load(SkinScale.X1)
        setScreen(screen)

        ResourceRouter.instance.route (
            GrlUtils.createPostRequest("redis://123", "456")
        )

        def result = ResourceRouter.instance.route (
            GrlUtils.createGetRequest("redis://123", "java.lang.String")
        )
    }

    @Override
    public void dispose() { VisUI.dispose() }
}
