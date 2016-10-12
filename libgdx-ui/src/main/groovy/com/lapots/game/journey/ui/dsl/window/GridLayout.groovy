package com.lapots.game.journey.ui.dsl.window

import com.kotcrab.vis.ui.building.GridTableBuilder
import com.kotcrab.vis.ui.building.utilities.Padding

/**
 * Represent grid layout for window.
 * Basically
 *
 * <pre>
 *     columns 10
 * </pre>
 */
class GridLayout {
    def grid
    def windowConfig = new WindowConfig()
    Padding padding = new Padding(windowConfig.horizontalPadding, windowConfig.verticalPadding);

    def columns(amount) { grid = new GridTableBuilder(padding, amount) }

    def build() {
        if (grid) { grid.build() }
        else { new GridTableBuilder(padding, 1).build() }
    }
}
