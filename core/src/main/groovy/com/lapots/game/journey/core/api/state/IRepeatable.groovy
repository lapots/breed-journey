package com.lapots.game.journey.core.api.state

// interface for objects that can repeat its state
interface IRepeatable {

    def change_state(next)
}
