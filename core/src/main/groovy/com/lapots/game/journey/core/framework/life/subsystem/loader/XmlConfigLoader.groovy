package com.lapots.game.journey.core.framework.life.subsystem.loader

import com.lapots.game.journey.core.loader.ServiceConfigLoader

abstract class XmlConfigSubsystemLoader {

    def load(file, subsystem) {
        def xml = new ServiceConfigLoader().load(file)
        processXml(xml.'*'.find { node -> node.@id == subsystemId() }, subsystem)
    }

    abstract subsystemId()
    abstract processXml(xml, subsystem)
}
