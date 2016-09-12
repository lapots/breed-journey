package com.lapots.game.journey.core.platform.resource.router.ui

import com.lapots.game.journey.core.api.IRouter
import com.lapots.game.journey.core.platform.resource.storage.ui.UiComponentStorage
import com.lapots.game.journey.util.GrlUtils

import org.springframework.stereotype.Component

import org.springframework.beans.factory.annotation.Autowired

@Component
class UiResourceRouter implements IRouter {

    @Autowired
    UiComponentStorage uiStorage

    def channels = [
        "GET" : { route_key, message ->
            uiStorage.read(route_key)
        }
    ]

    def route(grl_message) {
        def key = GrlUtils.getRouteKey(grl_message.requestGRL)
        channels [grl_message.requestType] (key, grl_message)
    }
}
