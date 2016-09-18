package com.lapots.game.journey.ims.api

import com.lapots.game.journey.ims.domain.GRLMessage

/**
 * Interface for IMS consumers.
 */
interface IIMSConsumer : IIMSIdentifiable {
    fun consume(message : GRLMessage)
}
