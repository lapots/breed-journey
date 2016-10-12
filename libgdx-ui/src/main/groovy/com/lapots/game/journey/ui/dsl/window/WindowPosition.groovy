package com.lapots.game.journey.ui.dsl.window

import com.lapots.game.journey.util.EvaluationUtils

/**
 * Represent window position.
 */
class WindowPosition {
    def referencedWindow

    def x(x) { referencedWindow.setX(x) }

    def y(y) { referencedWindow.setY(y) }

    static offsetWindow(window) {
        def xOffsetVal = EvaluationUtils.evaluateWithBinding(WindowConfig.xOffset, window) as int
        def yOffsetVal = EvaluationUtils.evaluateWithBinding(WindowConfig.yOffset, window) as int
        window.setX(xOffsetVal)
        window.setY(yOffsetVal)
    }
}
