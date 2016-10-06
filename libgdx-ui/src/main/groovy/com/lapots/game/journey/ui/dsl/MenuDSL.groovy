package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.widget.Menu
import com.kotcrab.vis.ui.widget.MenuItem
import com.lapots.game.journey.util.DslUtils;

class MenuDSL {

    static final def LABEL_FIELD = "name"

    def entry_header
    @Lazy Menu menu = new Menu(entry_header)

    def item(map, closure) {
        def menuItem = new MenuItemDSL(item_label : map[LABEL_FIELD])
        DslUtils.delegate(closure, menuItem)

        menu.addItem(~menuItem)
    }

    def bitwiseNegate() { menu }
}
