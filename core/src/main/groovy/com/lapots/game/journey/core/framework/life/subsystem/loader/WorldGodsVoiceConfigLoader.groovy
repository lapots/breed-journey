package com.lapots.game.journey.core.framework.life.subsystem.loader

class WorldGodsVoiceConfigLoader extends XmlConfigSubsystemLoader {

    def subsystemId() { "godsVoice" }

    def processXml(xml, subsystem) {
        subsystem.component.innerId = xml.innerId.text()
        subsystem.component.wait = xml.wait.text() as Long
        subsystem.component.messages = xml.messages.text().trim().split("!").collect { it.trim() } as String[]
        subsystem.component.isSequenced = Boolean.valueOf((String)xml.messages.@sequenced)
    }
}
