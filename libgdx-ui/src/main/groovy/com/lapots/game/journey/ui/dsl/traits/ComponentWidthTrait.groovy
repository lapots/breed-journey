package com.lapots.game.journey.ui.dsl.traits

import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.building.utilities.CellWidget;

// Trait for components that have fixed width
trait ComponentWidthTrait {

    static final COMPONENT_WIDTH = 150

    def roundify(to_adjust, width) {
        CellWidget.of(to_adjust).width(width).wrap()
    }

    def roundify(to_adjust) {
        CellWidget.of(to_adjust).width(COMPONENT_WIDTH).wrap()
    }
}
