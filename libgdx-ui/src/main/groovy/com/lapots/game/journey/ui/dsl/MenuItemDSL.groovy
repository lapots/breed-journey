package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.widget.MenuItem
import com.lapots.game.journey.core.platform.UiPlatform.Constants;
import com.lapots.game.journey.util.ReflectionUtils;

class MenuItemDSL {

    def item_label
    @Lazy MenuItem item = new MenuItem(item_label)

    def on_click(closure) {
        def instance = ReflectionUtils.instantiate("$Constants.EVENT_PACKAGE.${ closure() }")
        item.addListener(instance)
    }

    def bitwiseNegate() { item }
}
