package com.lapots.game.journey.ui.dsl.menu

import com.kotcrab.vis.ui.widget.Menu
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils;

/**
 * Dsl for menu entry.
 *
 * {@link MenuBarDSL}.
 */
class MenuDSL implements CompositeTrait {

    def entryName
    @Lazy Menu menu = new Menu(entryName)

    //=====================DSL specifics====================
    def item(map, closure) {
        id = "menu-" + uuid()
        def menuItem = new MenuItemDSL(itemName : map[UiHelper["dsl.config.menu_label_key"]], id: "menuitem-" + uuid())
        menuItem.parentUid = this.id
        DslUtils.delegate(closure, menuItem)

        menu.addItem(menuItem.getInnerComponent())

        UiHelper.componentRegistry[(id)] = this
        UiHelper.componentRegistry[(menuItem.id)] = menuItem
    }
    //======================================================

    @Override
    def enumerateChildren() {
        this.ids.each {}
    }

    @Override
    def appendChild(Object child) { menu.addItem(child.getInnerComponent()) }

    @Override
    def getInnerComponent() { return menu }

    @Override
    def getRawComponent() { return menu }
}
