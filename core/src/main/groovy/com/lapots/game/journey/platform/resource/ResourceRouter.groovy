package com.lapots.game.journey.platform.resource

import com.lapots.game.journey.util.GrlUtils;

class ResourceRouter {

    static def routers = [
        "redis://" : { grl_message ->
            RedisResourceRouter.process(grl_message)
        }
    ]

    static send(grl_message) {
        routers [GrlUtils.getRouteResolver(grl_message.requestGRL)](grl_message)
    }
}
