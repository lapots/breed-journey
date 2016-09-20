package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.api.IChannel
import com.lapots.game.journey.ims.api.IGRLMultipart
import com.lapots.game.journey.ims.api.IRouter
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLMethod
import com.lapots.game.journey.ims.domain.GRLPackage


data class DummyMultipart(val field: String) : IGRLMultipart {
    override fun getContent() : Any {
        return this
    }
}

fun dummyMessage() : GRLMessage {
    return GRLMessage().message {
        method { GRLMethod.PUT }
        header { "defaultHeader" to "example" }
        multipart { DummyMultipart("multipart") }
    }
}

fun stubRouter() : IRouter {
    return object : IRouter {
        val routes = mutableMapOf("ui:component" to "nothing")
        override fun process(pack : GRLPackage) {
            if (routes[pack.grl] == null) {
                throw IMSException("Router cannot process grl!")
            }
        }
        override fun registerChannel(name : GRLMethod, channel : IChannel) {}

        override fun registerRoute(route: String) { }

        override fun isSupport(route: String): Boolean { return true }

        override fun getRoutes(): List<String> {
            return routes.keys.toList()
        }
    }
}
