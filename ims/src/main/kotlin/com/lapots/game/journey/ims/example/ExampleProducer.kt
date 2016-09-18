package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.api.IIMSProducer
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLMethod

/**
 * Producer example.
 */
class ExampleProducer : IIMSProducer {
    val route = "example:package:route"

    override fun produce(): GRLMessage {
        val msg = dummyMessage()
        return IMSGate.warp(route, msg)
    }

    override fun getImsId(): String {
        return ""
    }

    override fun setImsId(id: String) { }
}
