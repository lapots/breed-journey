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
        id = uuid()
        def menuItem = new MenuItemDSL(itemName : map[UiHelper["dsl.config.menu_label_key"]], id: uuid())
        DslUtils.delegate(closure, menuItem)

        menu.addItem(menuItem.getInnerComponent())
    }
    //======================================================

    @Override
    Object getParentUid() { return parentUid }

    @Override
    void setParentUid(Object parentUid) { this.parentUid = parentUid }

    @Override
    Object getId() { return id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def enumerateChildren() {
        this.ids.each {}
    }

    @Override
    def getInnerComponent() { return menu }

    @Override
    def getRawComponent() { return menu }
}
