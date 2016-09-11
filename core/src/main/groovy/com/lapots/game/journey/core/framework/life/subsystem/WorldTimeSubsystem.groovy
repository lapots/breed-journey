package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.framework.Subsystem
import com.lapots.game.journey.core.framework.life.subsystem.state.WorldTime

class WorldTimeSubsystem extends Subsystem {

    def component = new WorldTime()

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
