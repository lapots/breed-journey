package com.lapots.game.journey.ui.dsl.menu

import com.kotcrab.vis.ui.widget.Menu
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils;

/**
 * Dsl for menu entry.
 *
 * {@link MenuBarDSL}.
 */
class MenuDSL {

    static final def LABEL_FIELD = UiHelper["dsl.config.menu_label_key"]

    def entry_header
    @Lazy Menu menu = new Menu(entry_header)

    def item(map, closure) {
        def menuItem = new MenuItemDSL(item_label : map[LABEL_FIELD])
        DslUtils.delegate(closure, menuItem)

        menu.addItem(~menuItem)
    }

    def bitwiseNegate() { menu }
}
