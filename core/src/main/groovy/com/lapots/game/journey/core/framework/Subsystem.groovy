package com.lapots.game.journey.core.framework

abstract class Subsystem {
    def component

    abstract def activate()
    abstract def disable()
}
