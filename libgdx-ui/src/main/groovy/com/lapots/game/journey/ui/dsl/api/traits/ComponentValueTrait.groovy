package com.lapots.game.journey.ui.dsl.api.traits

/**
 * Trait for components that can have values.
 * Default trait for primitives.
 */
trait ComponentValueTrait {
    // allows to calculate component return value
    // in need
    abstract def getValue()

    // allows to set value of the component
    abstract def setValue(def value)
}
