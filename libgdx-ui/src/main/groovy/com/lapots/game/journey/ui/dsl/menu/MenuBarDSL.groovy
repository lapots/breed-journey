package com.lapots.game.journey.ui.dsl.menu

import com.kotcrab.vis.ui.widget.MenuBar
import com.lapots.game.journey.core.api.IReferenced
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
class MenuBarDSL implements IReferenced {

    static final def HEADER_FIELD = UiHelper["dsl.config.menu_label_key"]

    @Lazy MenuBar menubar = new MenuBar()

    def call(closure) {
        // basically menu bar holds whole screen.
        UiHelper.root.add(menubar.getTable()).expandX().fillX().row()
        UiHelper.root.add().expand().fill()

        DslUtils.delegate(closure, this)
    }

    def menu(map, closure) {
        def menuEntry = new MenuDSL(entry_header : map[HEADER_FIELD])
        DslUtils.delegate(closure, menuEntry)

        menubar.addMenu(~menuEntry)
    }

    def component_reference() { null }

    def bitwiseNegate() { menubar }
}

