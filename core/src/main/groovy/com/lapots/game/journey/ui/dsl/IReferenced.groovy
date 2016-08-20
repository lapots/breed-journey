package com.lapots.game.journey.ui.dsl

interface IReferenced {
    // method returns not null ONLY if component can store others
    def component_reference()
    def bitwiseNegate()
}
