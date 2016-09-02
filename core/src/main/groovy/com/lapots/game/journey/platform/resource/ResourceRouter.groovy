package com.lapots.game.journey.platform.resource

import com.lapots.game.journey.platform.resource.router.redis.RedisResourceRouter
import com.lapots.game.journey.platform.resource.router.ui.UiResourceRouter

import com.lapots.game.journey.platform.core.router.IRouter
import com.lapots.game.journey.util.GrlUtils;

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

@Component
class ResourceRouter implements IRouter {

    @Autowired
    RedisResourceRouter redisRouter

    @Autowired
    UiResourceRouter uiRouter

    def channels = [
        "redis://" : { grl_message ->
           redisRouter.route(grl_message)
        },
        "ui://" : { grl_message ->
            uiRouter.route(grl_message)
        }
    ]

    def route(grl_message) {
        channels [GrlUtils.getRouteResolver(grl_message.requestGRL)](grl_message)
    }

    def leftShift(grl_message) {
        route(grl_message)
    }

    def rightShift(grl_message) {
        route(grl_message)
    }
}
