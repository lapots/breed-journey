package com.lapots.game.journey.ui.dsl.traits

// Trait for components that have values to read
trait ValueReferencedTrait {

    def valueRef

    abstract def getValue()
}
