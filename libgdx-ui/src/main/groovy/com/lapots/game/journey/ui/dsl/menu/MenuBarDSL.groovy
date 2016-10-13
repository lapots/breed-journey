package com.lapots.game.journey.ui.dsl.menu

import com.kotcrab.vis.ui.widget.MenuBar
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
import com.lapots.game.journey.ui.helper.UiHelper;
import com.lapots.game.journey.util.DslUtils;

/**
 * Represent application menu bar.
 *
 * Example of current
 *
 *      menuBar {
 *          menu(name: 'File') {
 *              item(name: 'New..') {
 *                  on_click {
 *                      "SomeCustomEvent"
 *                  }
 *              }
 *          }
 *      }
 *
 */
class MenuBarDSL implements CompositeTrait {

    @Lazy MenuBar menubar = new MenuBar()

    //=============================DSL specificis=====================
    def call(closure) {
        this.id = "menubar-" + uuid()
        // basically menu bar holds whole screen.
        UiHelper.root.add(menubar.getTable()).expandX().fillX().row()
        UiHelper.root.add().expand().fill()

        DslUtils.delegate(closure, this)

        UiHelper.componentRegistry[(id)] = this
    }

    def menu(map, closure) {
        def menuEntry = new MenuDSL(entryName : map[UiHelper["dsl.config.menu_label_key"]])
        menuEntry.parentUid = this.id
        DslUtils.delegate(closure, menuEntry)

        menubar.addMenu(menuEntry.getInnerComponent())
    }
    //====================================END==========================

    @Override
    def enumerateChildren() {
        // read every component
        this.ids.each {}
    }

    @Override
    def appendChild(Object child) { menubar.addMenu(child.getInnerComponent()) }

    @Override
    def getInnerComponent() { return menubar }

    @Override
    def getRawComponent() { return menubar }
}

