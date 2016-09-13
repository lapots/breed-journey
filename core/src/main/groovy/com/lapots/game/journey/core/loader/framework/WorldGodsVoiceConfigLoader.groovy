package com.lapots.game.journey.core.loader.framework

import com.lapots.game.journey.core.api.AbstractXmlConfigObjectLoader
import com.lapots.game.journey.util.StringUtils;

class WorldGodsVoiceConfigLoader extends AbstractXmlConfigObjectLoader {

    def identifier() { "godsVoice" }

    def processXml(xml, component) {
        component.innerId = xml.innerId.text()
        component.wait = xml.wait.text() as Long
        component.messages = StringUtils.clearSplit(xml.messages.text(), "!")
        component.isSequenced = Boolean.valueOf((String)xml.messages.@sequenced)
    }
}
