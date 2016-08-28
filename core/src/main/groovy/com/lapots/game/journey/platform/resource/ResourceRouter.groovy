package com.lapots.game.journey.platform.resource

import com.lapots.game.journey.platform.resource.router.redis.RedisResourceRouter
import com.lapots.game.journey.platform.resource.router.ui.UiResourceRouter

import com.lapots.game.journey.platform.core.router.IRouter
import com.lapots.game.journey.util.GrlUtils;

@Singleton
class ResourceRouter implements IRouter {

    def channels = [
        "redis://" : { grl_message ->
            RedisResourceRouter.instance.route(grl_message)
        },
        "ui://" : { grl_message ->
            UiResourceRouter.instance.route(grl_message)
        }
    ]

    def route(grl_message) {
        channels [GrlUtils.getRouteResolver(grl_message.requestGRL)](grl_message)
    }
}
