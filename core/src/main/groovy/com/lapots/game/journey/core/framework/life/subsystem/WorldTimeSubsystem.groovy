package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.framework.Subsystem

class WorldTimeSubsystem extends Subsystem {

    def component = new WorldClock()

    def activate() {
        component.start()
    }

    def disable() {
        component.stopThread()
    }

    String toString() {
        component
    }
}
