package com.lapots.game.journey.core.framework.life

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.framework.ComplexFramework
import com.lapots.game.journey.core.framework.life.subsystem.GodsVoiceSubsystem
import com.lapots.game.journey.core.framework.life.subsystem.WorldTimeSubsystem
import com.lapots.game.journey.core.framework.life.subsystem.loader.WorldGodsVoiceConfigLoader
import com.lapots.game.journey.core.framework.life.subsystem.loader.WorldTimeConfigLoader
import com.lapots.game.journey.core.loader.ServiceConfigLoader

import org.springframework.stereotype.Component

@Component
class LifeFramework extends ComplexFramework {

    static final service_config = "config/service-config.xml";

    def file

    {
        file = Gdx.files.internal(service_config).file

        loaders <<      [ "worldTime" : new WorldTimeConfigLoader() ]
        subsystems <<   [ "worldTime" : new WorldTimeSubsystem() ]

        loaders <<      [ "godsVoice" : new WorldGodsVoiceConfigLoader() ]
        subsystems <<   [ "godsVoice" :  new GodsVoiceSubsystem() ]
    }

    def initSubsystems() {
        loaders.each { k, v -> v.load(file, subsystems[k]) }

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
