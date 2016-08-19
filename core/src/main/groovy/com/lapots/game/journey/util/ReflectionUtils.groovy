package com.lapots.game.journey.util

class ReflectionUtils {

    static instantiate(name) {
        Class.forName(name).newInstance()
    }
}
