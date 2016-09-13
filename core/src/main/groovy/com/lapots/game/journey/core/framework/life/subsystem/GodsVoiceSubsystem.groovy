package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.api.Subsystem
import com.lapots.game.journey.core.framework.life.LifeFramework;
import com.lapots.game.journey.core.framework.life.subsystem.component.WorldGodsVoice
import com.lapots.game.journey.core.loader.framework.WorldGodsVoiceConfigLoader

class GodsVoiceSubsystem extends Subsystem {

    def component = new WorldGodsVoice();

    {
        new WorldGodsVoiceConfigLoader().load(LifeFramework.file, component)
    }

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
