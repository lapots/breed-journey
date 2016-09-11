package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.framework.Subsystem
import com.lapots.game.journey.core.framework.life.subsystem.state.WorldGodsVoice

class GodsVoiceSubsystem extends Subsystem {

    def component = new WorldGodsVoice()

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
