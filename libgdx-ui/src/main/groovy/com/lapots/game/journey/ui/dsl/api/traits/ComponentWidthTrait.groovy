package com.lapots.game.journey.ui.dsl.api.traits

import com.kotcrab.vis.ui.building.utilities.CellWidget
import com.lapots.game.journey.ui.helper.UiHelper

/**
 * Trait for component that can be resized to
 * certain width. Usually cell elements.
 */
trait ComponentWidthTrait {

    static final COMPONENT_WIDTH = UiHelper["component.config.component_width"] as int

    // creates CellWidth out of component and set its width.
    def roundify(component, int width) {
        CellWidget.of(component).width(width).wrap()
    }

    def roundify(component) {
        CellWidget.of(component).width(COMPONENT_WIDTH).wrap()
    }
}
