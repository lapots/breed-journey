package com.lapots.game.journey.ui.dsl.api

import com.lapots.game.journey.ui.dsl.api.traits.IdentifiableTrait

/**
 * Core component for DSL.
 * Represent any element.
 *
 * Inherit ability to identify component
 * from {@link IdentifiableTrait}.
 */
interface IComponentDSL extends IdentifiableTrait {
    def getInnerComponent()
    def getRawComponent()
}