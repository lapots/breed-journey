package com.lapots.game.journey.platform

import com.lapots.game.journey.platform.resource.ResourceRouter;

class ResourcePlatform extends ManagedPlatform {

    static resources = managed["resourceRouter"]

    static leftShift(request) {
        resources << request
    }

    static rightShift(request) {
        resources >> request
    }
}
