package com.lapots.game.journey.platform.resource.router.ui

import com.lapots.game.journey.platform.core.router.IRouter
import com.lapots.game.journey.platform.resource.storage.ui.UiComponentStorage
import com.lapots.game.journey.util.GrlUtils

@Singleton
class UiResourceRouter implements IRouter {

    def channels = [
        "GET" : { route_key, message ->
            UiComponentStorage.instance.read(route_key)
        }
    ]

    def route(grl_message) {
        def key = GrlUtils.getRouteKey(grl_message.requestGRL)
        channels [grl_message.requestType] (key, grl_message)
    }
}
