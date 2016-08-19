package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.widget.MenuItem
import com.lapots.game.journey.ui.UiConstants
import com.lapots.game.journey.util.ReflectionUtils;

class MenuItemDSL {

    def item_label
    @Lazy MenuItem item = new MenuItem(item_label)

    def on_change(closure) {
        def instance = ReflectionUtils.instantiate("$UiConstants.EVENT_PACKAGE.${ closure() }")
        item.addListener(instance)
    }

    def bitwiseNegate() { item }
}
