package com.lapots.game.journey.ims.api

import com.lapots.game.journey.ims.domain.GRLMessage

/**
 * Interface for IMS producers.
 */
interface IIMSProducer : IIMSIdentifiable {
    fun produce() : GRLMessage
    fun util_produce(destination: String) : GRLMessage
}
