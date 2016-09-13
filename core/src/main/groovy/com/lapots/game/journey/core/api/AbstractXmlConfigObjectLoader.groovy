package com.lapots.game.journey.core.api

import com.lapots.game.journey.core.loader.XmlLoader

abstract class AbstractXmlConfigObjectLoader {

    def load(file, object) {
        def xml = new XmlLoader().load(file)
        processXml(xml.'*'.find { node -> node.@id == identifier() }, object)
    }

    abstract identifier()
    abstract processXml(xml, object)
}
