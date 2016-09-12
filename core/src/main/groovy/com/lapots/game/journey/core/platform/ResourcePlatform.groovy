package com.lapots.game.journey.core.platform

class ResourcePlatform extends ManagedPlatform {

    static resources = managed["resourceRouter"]

    static leftShift(request) {
        resources << request
    }

    static rightShift(request) {
        resources >> request
    }
}
