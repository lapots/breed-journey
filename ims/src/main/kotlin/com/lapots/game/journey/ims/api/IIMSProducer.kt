package com.lapots.game.journey.ims.api

import com.lapots.game.journey.ims.domain.GRLMessage

/**
 * Interface for IMS producers.
 */
interface IIMSProducer : IIMSIdentifiable {
    fun produce() : GRLMessage
    fun produce(destination: String) : GRLMessage
}
