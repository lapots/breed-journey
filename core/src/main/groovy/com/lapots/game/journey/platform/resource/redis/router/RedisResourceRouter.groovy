package com.lapots.game.journey.platform.resource.redis.router

import com.lapots.game.journey.core.api.IRouter
import com.lapots.game.journey.util.GrlUtils;;

import org.springframework.stereotype.Component

import org.springframework.beans.factory.annotation.Autowired

@Component
class RedisResourceRouter implements IRouter {

    @Autowired
    RedisObjectRouter redis_object_router

    def channels = [
        "POST" : { route_key, message ->
            redis_object_router.write(route_key, message.content)
        },
        "GET" : { route_key, message ->
            def expected_type = message.headers["expected type"]
            redis_object_router.read(route_key, expected_type)
        }
    ]

    def route(grl_message) {
        def key = GrlUtils.getRouteKey(grl_message.requestGRL)
        channels [grl_message.requestType](key, grl_message)
    }

}
