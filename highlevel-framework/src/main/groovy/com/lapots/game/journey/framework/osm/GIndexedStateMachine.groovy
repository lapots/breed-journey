package com.lapots.game.journey.framework.osm

import com.lapots.game.journey.framework.interop.StaticScalaInterop
import com.lapots.game.journey.osm.domain.ObjectState

/**
 * Wrapper over Scala state machine.
 *
 * But due to dynamic Groovy nature you don't feel it.
 */
class GIndexedStateMachine {
    // supposed to operate with GObjectStates
    def states = []
    def currentIndex = 0

    def add_state(state) { states << state }

    def current_state() {
        if (states.size() == 0) {
            return null
        }
        states[currentIndex]
    }

    def next_state() {
        currentIndex++
        if (currentIndex > states.size()) {
            currentIndex = 0
        }
        apply_state()
    }

    def next_state(index) {
        currentIndex = index
        apply_state()
    }

    def previous_state() {
        currentIndex--
        if (currentIndex < 0) {
            currentIndex = states.size() - 1
        }
        apply_state()
    }

    private apply_state() {
        def state = states[currentIndex]
        // update object state
        state.writeStateToObject()
        state
    }

}
