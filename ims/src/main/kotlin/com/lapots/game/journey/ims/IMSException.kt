package com.lapots.game.journey.ims

/**
 * IMS default exceptions.
 */
class IMSException : RuntimeException {
    constructor(message : String, cause: Throwable) : super(message, cause)
    constructor(message : String) : super(message)
}
