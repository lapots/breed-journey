package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.ui.dsl.custom.DSLang

trait DynamicPropertyTrait {

    def propertyMissing(String name) {
        DSLang.key_words[name]()
    }
}
