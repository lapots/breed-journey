package com.lapots.game.journey.core.framework.life

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.ComplexFramework
import com.lapots.game.journey.core.framework.life.subsystem.GodsVoiceSubsystem
import com.lapots.game.journey.core.framework.life.subsystem.WorldTimeSubsystem
import com.lapots.game.journey.core.loader.XmlLoader
import com.lapots.game.journey.core.loader.framework.WorldGodsVoiceConfigLoader
import com.lapots.game.journey.core.loader.framework.WorldTimeConfigLoader

import org.springframework.stereotype.Component

@Component
class LifeFramework extends ComplexFramework {

    static final service_config = "config/service-config.xml"

    static file

    {
        file = Gdx.files.internal(service_config).file

        subsystems <<   [ "worldTime" : new WorldTimeSubsystem() ]
        subsystems <<   [ "godsVoice" :  new GodsVoiceSubsystem() ]
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
