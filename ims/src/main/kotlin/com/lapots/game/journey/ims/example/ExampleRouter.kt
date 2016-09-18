package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.api.IChannel
import com.lapots.game.journey.ims.api.IRouter
import com.lapots.game.journey.ims.domain.GRLMethod
import com.lapots.game.journey.ims.domain.GRLPackage

/**
 * Example router.
 */
class ExampleRouter(val path : String) : IRouter {
    val channels  = mutableMapOf<GRLMethod, IChannel>()

    override fun process(pack : GRLPackage) : GRLPackage {
        val msg = pack.message
        val channel = channels[msg.methodType]
        GRLPackage(pack.grl, channel!!.processMessage(msg))
        // hm provide some [response] grl maybe
        return GRLPackage(pack.grl, channel!!.processMessage(msg))
    }

    override fun registerChannel(name : GRLMethod, channel : IChannel) {
        if (channels[name] != null) {
            throw IMSException("This channel already exist!")
        }
        channels[name] = channel
    }
}
