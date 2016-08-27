package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.platform.resource.ResourceConnection
import com.lapots.game.journey.platform.resource.ResourceRouter
import com.lapots.game.journey.platform.resource.protocol.GRLMessage;
import com.lapots.game.journey.platform.resource.storage.RedisResourceStorage

class ApplicationMain extends Game {

    @Override
    public void create() {
        VisUI.load(SkinScale.X1)
        setScreen(new ApplicationMenuScreen())

        ResourceRouter.send (
            new GRLMessage()
                .withRequestType("POST")
                .withRequestGRL("redis://123")
                .withContent("456")
        )

        def result = ResourceRouter.send (
            new GRLMessage()
                .withRequestType("GET")
                .withRequestGRL("redis://123")
        )
        println result
    }

    @Override
    public void dispose() { VisUI.dispose() }
}
