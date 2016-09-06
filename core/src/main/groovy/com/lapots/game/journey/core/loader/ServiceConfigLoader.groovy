package com.lapots.game.journey.core.loader

import com.lapots.game.journey.core.api.ILoader

class ServiceConfigLoader implements ILoader {

    def load(file) {
        new XmlSlurper().parse(file)
    }
}
