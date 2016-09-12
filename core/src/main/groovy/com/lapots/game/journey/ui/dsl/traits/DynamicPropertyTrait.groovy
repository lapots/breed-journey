package com.lapots.game.journey.ui.dsl.traits

import com.lapots.game.journey.ui.dsl.custom.DSLang


// Trait for components that support DSLang
trait DynamicPropertyTrait {

    def propertyMissing(String name) {
        DSLang.key_words[name]()
    }
}
