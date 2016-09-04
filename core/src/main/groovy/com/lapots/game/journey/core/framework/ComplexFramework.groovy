package com.lapots.game.journey.core.framework

abstract class ComplexFramework {

    def subsystems = [:]

    abstract initSubsystems()
    abstract destroySubsystems()
    abstract accessSubsystem(id)
}
