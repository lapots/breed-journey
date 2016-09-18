package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.api.IChannel
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLMethod

/**
 * Example channel.
 */
class ExampleChannel : IChannel {
    override fun processMessage(message: GRLMessage): GRLMessage {
        print("XXX message received!")
        val response = GRLMessage().message {
            method { GRLMethod.GET }
            header { "responseHeader" to "success" }
        }
        return response
    }
}
