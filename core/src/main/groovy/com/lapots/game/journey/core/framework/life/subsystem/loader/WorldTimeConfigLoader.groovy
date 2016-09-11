package com.lapots.game.journey.core.framework.life.subsystem.loader

class WorldTimeConfigLoader extends XmlConfigSubsystemLoader {

    def subsystemId() { "worldTime" }

    def processXml(xml, subsystem) {
        subsystem.component.clock.innerId = xml.innerId.text()
        subsystem.component.clock.wait = xml.wait.text() as Long
    }
}
