package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.api.Subsystem
import com.lapots.game.journey.core.framework.life.LifeFramework;
import com.lapots.game.journey.core.framework.life.subsystem.component.WorldTimeComponent
import com.lapots.game.journey.core.loader.framework.WorldTimeConfigLoader

class WorldTimeSubsystem extends Subsystem {

    def component = new WorldTimeComponent()

    {
        new WorldTimeConfigLoader().load(LifeFramework.file, component)
    }

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
