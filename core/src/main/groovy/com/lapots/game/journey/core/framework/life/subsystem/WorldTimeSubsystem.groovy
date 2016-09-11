package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.framework.Subsystem
import com.lapots.game.journey.core.framework.life.subsystem.state.WorldTime
import com.lapots.game.journey.core.framework.life.subsystem.state.WorldTimePlatform

class WorldTimeSubsystem extends Subsystem {

    def component = new WorldTimePlatform()

    def activate() {
        component.start()
    }

    def disable() {
        component.stop()
    }

    String toString() {
        component
    }
}
