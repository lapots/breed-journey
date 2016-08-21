package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.ApplicationControl;

trait DynamicPropertyTrait {

    def propertyMissing(String name) {
        ApplicationControl.class_registry[name]
    }
}
