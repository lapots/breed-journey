package com.lapots.game.journey.ui.dsl

// Trait for components that can contain other components
trait CompositeTrait {

    def ids = []

    def collect_data() {
        ids.each { id -> 
            println id
        }
    }
}
