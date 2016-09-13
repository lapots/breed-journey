package com.lapots.game.journey.core.loader.framework

import com.lapots.game.journey.core.api.AbstractXmlConfigObjectLoader

class WorldTimeConfigLoader extends AbstractXmlConfigObjectLoader {

    def identifier() { "worldTime" }

    def processXml(xml, component) {
        component.clock.innerId = xml.innerId.text()
        component.clock.wait = xml.wait.text() as Long
    }
}
