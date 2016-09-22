package com.lapots.game.journey.ims.api

/**
 * Abstract wrapper class for IMS
 */
abstract class AbstractIdentifiable<T> : IIMSConsumer, IIMSProducer {
     abstract val obj : T
}