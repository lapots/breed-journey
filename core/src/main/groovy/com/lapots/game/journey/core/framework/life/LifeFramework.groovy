package com.lapots.game.journey.core.framework.life

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.framework.ComplexFramework
import com.lapots.game.journey.core.framework.life.subsystem.GodsVoiceSubsystem
import com.lapots.game.journey.core.framework.life.subsystem.WorldTimeSubsystem
import com.lapots.game.journey.core.loader.ServiceConfigLoader

import org.springframework.stereotype.Component

@Component
class LifeFramework extends ComplexFramework {

    static final service_config = "service-config.xml";

    def config_action = [
        "worldClock" : { xml, subsystem ->
            subsystem.component.innerId = xml.innerId.text()
            subsystem.component.wait = xml.wait.text() as Long
        },
        "godsVoice"  : { xml, subsystem ->
            subsystem.component.innerId = xml.innerId.text()
            subsystem.component.wait = xml.wait.text() as Long
            subsystem.component.messages = xml.messages.text().trim().split("!").collect { it.trim() } as String[]
            subsystem.component.isSequenced = xml.messages.@sequenced
        }
    ];

    {
        subsystems << [ "godsVoice" :  new GodsVoiceSubsystem() ]
        subsystems << [ "worldClock" : new WorldTimeSubsystem() ]
    }

    def initSubsystems() {
        def services = new ServiceConfigLoader().load(
                Gdx.files.internal("config/service-config.xml").file
        )
        // config subsystems
        subsystems.each { k, v ->
            config_action[k](services.'*'.find { node -> node.@id == k } , v)
            println v
        }

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
