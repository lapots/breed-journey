package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.platform.core.protocol.GRLMessage
import com.lapots.game.journey.platform.resource.ResourceRouter

class ApplicationMain extends Game {

    @Override
    public void create() {
        VisUI.load(SkinScale.X1)
        setScreen(new ApplicationMenuScreen())

        ResourceRouter.instance.route (
            new GRLMessage()
                .withRequestType("POST")
                .withRequestGRL("redis://123")
                .withContent("456")
        )

        def result = ResourceRouter.instance.route (
            new GRLMessage()
                .withRequestType("GET")
                .withContent("java.lang.String")
                .withRequestGRL("redis://123")
        )
        println result
    }

    @Override
    public void dispose() { VisUI.dispose() }
}
