package com.lapots.game.journey.util

class DslUtils {

    static delegate(closure, reference) {
        closure.delegate = reference
        closure.setResolveStrategy Closure.DELEGATE_ONLY
        closure()
    }
}
