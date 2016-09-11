package com.lapots.game.journey.core.framework.life.subsystem.loader

import com.lapots.game.journey.util.StringUtils;

class WorldGodsVoiceConfigLoader extends XmlConfigSubsystemLoader {

    def subsystemId() { "godsVoice" }

    def processXml(xml, subsystem) {
        subsystem.component.innerId = xml.innerId.text()
        subsystem.component.wait = xml.wait.text() as Long
        subsystem.component.messages = StringUtils.clearSplit(xml.messages.text(), "!")
        subsystem.component.isSequenced = Boolean.valueOf((String)xml.messages.@sequenced)
    }
}
