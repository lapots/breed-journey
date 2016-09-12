package com.lapots.game.journey.core.api

abstract class Subsystem {
    def component

    abstract def activate()
    abstract def disable()
}
