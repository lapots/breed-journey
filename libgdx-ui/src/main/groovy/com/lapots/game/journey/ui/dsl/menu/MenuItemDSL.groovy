package com.lapots.game.journey.ui.dsl.menu

import com.kotcrab.vis.ui.widget.MenuItem
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.ReflectionUtils;

/**
 * Basic menu item.
 * Support [on_click] events.
 *
 * {@link MenuBarDSL}
 */
class MenuItemDSL {

    def item_label
    @Lazy MenuItem item = new MenuItem(item_label)

    /**
     * Dynamically add click listener (usually InputListener) to item component.
     */
    def on_click(closure) {
        def instance = ReflectionUtils.instantiateOne(UiHelper["application.packages.event_packages"], closure())
        item.addListener(instance)
    }

    def bitwiseNegate() { item }
}
