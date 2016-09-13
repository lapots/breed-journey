package com.lapots.game.journey.core.loader

import com.lapots.game.journey.core.api.ILoader

class XmlLoader implements ILoader {

    def load(file) {
        new XmlSlurper().parse(file)
    }
}
