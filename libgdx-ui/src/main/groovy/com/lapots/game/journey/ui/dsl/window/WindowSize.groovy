package com.lapots.game.journey.ui.dsl.window

/**
 * Represent window size.
 */
class WindowSize {
    def referencedWindow

    def width(w) { referencedWindow.setWidth(w) }

    def height(h) { referencedWindow.setHeight(h) }
}
