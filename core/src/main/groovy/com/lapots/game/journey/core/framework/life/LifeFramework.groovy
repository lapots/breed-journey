package com.lapots.game.journey.core.framework.life

import com.lapots.game.journey.core.framework.ComplexFramework
import com.lapots.game.journey.core.framework.life.subsystem.GodsVoiceSubsystem
import com.lapots.game.journey.core.framework.life.subsystem.WorldTimeSubsystem

class LifeFramework extends ComplexFramework {

    {
        subsystems << [ "WORLD_TIME" : new WorldTimeSubsystem() ]
        subsystems << [ "GODS_VOICE" : new GodsVoiceSubsystem() ]
    }

    def initSubsystems() {
        subsystems.each { k, v ->
            v.activate()
        }
    }

    def destroySubsystems() {
        subsystems.each { k, v ->
            v.disable()
        }
    }

    def accessSubsystem(id) {
        subsystems[id]
    }
}
