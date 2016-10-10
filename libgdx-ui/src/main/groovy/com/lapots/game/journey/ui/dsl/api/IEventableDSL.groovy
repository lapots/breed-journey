package com.lapots.game.journey.ui.dsl.api

/**
 * Interface for DSL components that support
 * events (onClick for example).
 */
interface IEventableDSL {
    def onClick(closure)
}