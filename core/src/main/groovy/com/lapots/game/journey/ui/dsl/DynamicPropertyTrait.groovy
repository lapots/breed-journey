package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.platform.CorePlatform

trait DynamicPropertyTrait {

    def propertyMissing(String name) {
        CorePlatform.managed[name] // assuming it won't move further than managed scope
    }
}
