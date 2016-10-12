package com.lapots.game.journey.ui.dsl.api

/**
 * Represent components that
 * can store other components.
 */
interface ICompositeDSL extends IComponentDSL {
    def enumerateChildren()

    def appendChild(child)
}