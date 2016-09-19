package com.lapots.game.journey.ims.api

import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLMethod

/**
 * Interface for channels.
 * Channel is an object that provides routes to transport object.
 *
 * Channels should be mapped to router.
 * Usually channel supported by certain router but the same channel can available
 * for multiple routers.
 *
 * Generally channels separated by supported GRLMethod - POST, PUT, GET channels.
 * Channels works with messages instead of packages. Also the processing
 * message result is a message too.
 */
interface IChannel {
    fun processMessage(message : GRLMessage)
}
