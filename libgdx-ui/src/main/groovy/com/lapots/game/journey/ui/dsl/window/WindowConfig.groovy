package com.lapots.game.journey.ui.dsl.window

import com.kotcrab.vis.ui.util.TableUtils
import com.lapots.game.journey.ui.helper.UiHelper

/**
 * Stores window configuration.
 */
class WindowConfig {
    static horizontalPadding = UiHelper["component.config.padding.horizontal"] as int
    static verticalPadding = UiHelper["component.config.padding.vertical"] as int
    static xOffset = UiHelper["component.config.window_center_offset"] as int
    static yOffset = UiHelper["component.config.window_center_offset"] as int

    static windowTableConfig(window) {
        TableUtils.setSpacingDefaults(window);
        window.defaults().padRight(5);
        window.defaults().padLeft(5);
        window.columnDefaults(0).left();
    }
}
