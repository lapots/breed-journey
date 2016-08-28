package com.lapots.game.journey.platform.resource.router.redis

import com.lapots.game.journey.platform.core.router.IRouter
import com.lapots.game.journey.util.GrlUtils;;

@Singleton
class RedisResourceRouter implements IRouter {

    def redis_object_router = RedisObjectRouter.instance

    def channels = [
        "POST" : { route_key, message ->
            redis_object_router.write(route_key, message.content)
        },
        "GET" : { route_key, message ->
            redis_object_router.read(route_key, message.content)
        }
    ]

    def route(grl_message) {
        def key = GrlUtils.getRouteKey(grl_message.requestGRL)
        channels [grl_message.requestType](key, grl_message)
    }

}
