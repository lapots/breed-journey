package com.lapots.game.journey.util

class StringUtils {

    static clearSplit(text, separator) {
        text.replaceAll("\\s+", " ").trim().split(separator).collect { it.trim() }
    }
}
