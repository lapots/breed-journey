package com.lapots.game.journey.ui.dsl.traits

// means that DSL has unique index and registered in global dsl pool
trait IdentifiableTrait {
    def id

    static uuid() { UUID.randomUUID().toString() }

    def id(id) {
        this.id = id
    }

    abstract def identifiable_instance()
}