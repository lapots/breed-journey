package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.api.IIMSConsumer
import com.lapots.game.journey.ims.domain.GRLMessage

/**
 * Consumer example.
 */
class ExampleConsumer : IIMSConsumer {
    override fun setImsId(id: String) {
    }

    override fun getImsId(): String {
        return ""
    }

    override fun consume(message: GRLMessage) {
    }
}
