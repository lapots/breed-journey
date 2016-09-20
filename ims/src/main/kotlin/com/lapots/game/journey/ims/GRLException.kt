package com.lapots.game.journey.ims

/**
 * Exception for GRL protocol related objects.
 */
class GRLException : RuntimeException {
    constructor(message : String, cause: Throwable) : super(message, cause)
    constructor(message : String) : super(message)
}