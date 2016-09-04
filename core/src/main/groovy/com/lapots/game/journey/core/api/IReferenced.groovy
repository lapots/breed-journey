package com.lapots.game.journey.core.api

interface IReferenced {
    // method returns not null ONLY if component can store others
    def component_reference()
    def bitwiseNegate()
}
