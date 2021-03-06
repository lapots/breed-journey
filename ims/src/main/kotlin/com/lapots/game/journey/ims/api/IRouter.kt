package com.lapots.game.journey.ims.api

import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLPackage
import com.lapots.game.journey.ims.domain.GRLProtocol

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
    fun processMessage(msg: GRLMessage)
    fun registerRoute(route: String)
    fun supportedRoutes() : List<String>
}
