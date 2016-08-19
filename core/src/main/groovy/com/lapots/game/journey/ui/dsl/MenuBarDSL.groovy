package com.lapots.game.journey.ui.dsl

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.kotcrab.vis.ui.widget.Menu
import com.kotcrab.vis.ui.widget.MenuBar
import com.lapots.game.journey.ui.UiConstants
import com.lapots.game.journey.ui.UiControl
import com.lapots.game.journey.util.DslUtils;

class MenuBarDSL {

    static final def HEADER_FIELD = "name"

    @Lazy MenuBar menubar = new MenuBar()

    def call(closure) {
        UiControl.ui_root
                .add(menubar.getTable()).expandX().fillX().row()
        UiControl.ui_root
                .add().expand().fill()

        DslUtils.delegate(closure, this)
    }

    def menu(map, closure) {
        def menuEntry = new MenuDSL(entry_header : map[HEADER_FIELD])
        DslUtils.delegate(closure, menuEntry)

        menubar.addMenu(~menuEntry)
    }
}

