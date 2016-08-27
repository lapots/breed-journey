package com.lapots.game.journey.platform.resource

import com.lapots.game.journey.platform.resource.storage.RedisResourceStorage
import com.lapots.game.journey.util.GrlUtils;;

class RedisResourceRouter {

    static final PUT_REQUEST = "POST"
    static final GET_REQUEST = "GET"

    static def process(grl_message) {
        switch(grl_message.requestType) {
            case PUT_REQUEST:
                def key = GrlUtils.getRouteKey(grl_message.requestGRL)
                RedisResourceStorage.instance.write(key, grl_message.content)
                break
            case GET_REQUEST:
                def get_key = GrlUtils.getRouteKey(grl_message.requestGRL)
                RedisResourceStorage.instance.read(get_key)
                break
        }
    }
}
