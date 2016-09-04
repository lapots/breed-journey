package com.lapots.game.journey.util

class MathUtils {

    static randomFromList(list) {
        list[new Random().nextInt(list.size())]
    }
}
