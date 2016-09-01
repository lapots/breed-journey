package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.utilities.CellWidget;

trait ComponentWidthTrait {

    static final COMPONENT_WIDTH = 100

    def roundify(to_adjust) {
        CellWidget.of(to_adjust).width(COMPONENT_WIDTH).wrap()
    }
}