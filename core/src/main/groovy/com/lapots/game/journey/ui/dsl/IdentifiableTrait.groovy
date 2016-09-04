package com.lapots.game.journey.ui.dsl

// means that DSL has unique index and registered in global dsl pool
trait IdentifiableTrait {
    def id

    static uuid() { UUID.randomUUID().toString() }

    abstract def identifiable_instance()
}
