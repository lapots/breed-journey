package com.lapots.game.journey.ui.dsl.api.traits

import com.badlogic.gdx.scenes.scene2d.Actor

/**
 * Trait that supplies object with unique identifier
 * inside Libgdx ecosystem.
 */
trait IdentifiableTrait {
    def parentUid
    def id

    static uuid() { UUID.randomUUID().toString() }
}
