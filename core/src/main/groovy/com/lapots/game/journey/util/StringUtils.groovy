package com.lapots.game.journey.util

class StringUtils {

    static clearSplit(text, separator) {
        text.replaceAll(" +", "").trim().split(separator)
    }
}
