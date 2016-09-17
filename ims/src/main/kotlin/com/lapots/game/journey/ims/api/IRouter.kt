package com.lapots.game.journey.ims.api

import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.domain.GRLPackage

/**
 * Interface for routers.
 * Router is an object that works with channels.
 * Router should register supported channels in IMS context.
 *
 * Also every routers has names but which they can be referenced.
 * Additionally the can be referenced by supported routes.
 *
 * Router also should be able to process channels that they do not support
 * due to specific of IMSContext. At least they should throw IMSException.
 *
 * It is done to make routers flexible.
 */
interface IRouter {
    fun setPath(path : String)
    fun process(pack : GRLPackage) : GRLPackage
    fun registerChannel(name : String, channel : IChannel)

    // stub for router
    // might need to move to some specific stub class
    companion object {
        fun stubRouter() : IRouter {
            return object : IRouter {
                val routes = mutableMapOf("ui:component" to "nothing")
                override fun setPath(path : String) {}
                override fun process(pack : GRLPackage) : GRLPackage {
                    if (routes[pack.grl] == null) {
                        throw IMSException("Router cannot process grl!")
                    }
                    return GRLPackage.emptyPackage()
                }
                override fun registerChannel(name : String, channel : IChannel) {}
            }
        }
    }
}