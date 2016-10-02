package com.lapots.game.journey.framework.ims

/**
 * Some functions for Groovy -> Kotlin conversion.
 */
class KotlinBridgeUtils {

    static convertSingleMapToPair(map) {
        def singleEntry = map.entrySet()[0]
        new kotlin.Pair(singleEntry.key, singleEntry.value)
    }

}
