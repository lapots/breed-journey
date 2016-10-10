package com.lapots.game.journey.util

/**
 * Temporary class as I separating UI from implementation.
 */
class DslUtils {

    static delegate(closure, reference) {
        closure.delegate = reference
        closure.setResolveStrategy Closure.DELEGATE_ONLY
        closure()
    }
}
