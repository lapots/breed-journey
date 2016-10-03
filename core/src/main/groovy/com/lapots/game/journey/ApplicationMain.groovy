package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import com.lambdaworks.redis.RedisClient
import com.lapots.game.journey.core.framework.life.LifeFramework
import com.lapots.game.journey.core.platform.CorePlatform
import com.lapots.game.journey.core.platform.ResourcePlatform
import com.lapots.game.journey.framework.ims.GMessage
import com.lapots.game.journey.framework.osm.GIndexedStateMachine
import com.lapots.game.journey.framework.osm.GObjectState
import com.lapots.game.journey.ims.domain.GRLProtocol
import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL;
import com.lapots.game.journey.util.GrlUtils
import kotlin.Pair;

class ApplicationMain extends Game {

    class SimpleObject {
        def a = "5"
        def b = 10

        String toString() {
            "field[a]=$a, field[b]=$b"
        }
    }

    def grl_test() {
        def msg = new GMessage()
        msg {
            method { GRLProtocol.GRLMethod.POST }
            multipart { "hello world" }
            header { ["destination": "none"] }
        }
    }

    def state_machine_test() {
        def stateMachine = new GIndexedStateMachine()
        def obj = new SimpleObject()
        def state1 = new GObjectState(obj, ["a", "b"], [:])
        def state2 = new GObjectState(obj, ["a", "b"], ["a" : "111", "b" : null])

        stateMachine.add_state(state1) // first is default as index is 0
        stateMachine.add_state(state2)

        // current states 0 1
        println obj // original [0]
        stateMachine.next_state()
        println obj // after switching state forward [1]
        stateMachine.previous_state()
        println obj // after switching state backward [0]
        stateMachine.previous_state() // should become as it reached -1 and became [1]
        println obj // state [1]
    }

    @Override
    public void create() {
        state_machine_test()
        VisUI.load(SkinScale.X1)
        try {
            setScreen(new ApplicationMenuScreen())
            CorePlatform.managed["lifeFramework"].initSubsystems()
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
