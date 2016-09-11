package com.lapots.game.journey.core.api.state

abstract class AbstractStateMachine implements IRepeatable {

    def currentState
    def states = [:]

    def change_state(next) {
        currentState = states[next]
    }

    abstract populateStateMachine()
}
