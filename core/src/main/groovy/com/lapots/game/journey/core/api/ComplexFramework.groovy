package com.lapots.game.journey.core.api

abstract class ComplexFramework {

    def subsystems = [:]
    def loaders = [:]

    abstract initSubsystems()
    abstract destroySubsystems()
    abstract accessSubsystem(id)
}
