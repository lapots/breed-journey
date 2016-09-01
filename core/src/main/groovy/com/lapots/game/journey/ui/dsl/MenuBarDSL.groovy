package com.lapots.game.journey.ui.dsl

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.kotcrab.vis.ui.widget.Menu
import com.kotcrab.vis.ui.widget.MenuBar
import com.lapots.game.journey.platform.UiPlatform;
import com.lapots.game.journey.util.DslUtils;

class MenuBarDSL implements IReferenced {

    static final def IS_ROOT = "root"
    static final def HEADER_FIELD = "name"

    @Lazy Table root = new Table()
    @Lazy MenuBar menubar = new MenuBar()

    def call(closure) {
        root.setFillParent(true)
        root.add(menubar.getTable()).expandX().fillX().row()
        root.add().expand().fill()
        UiPlatform.default_stage.addActor(root)

        DslUtils.delegate(closure, this)
    }

    def menu(map, closure) {
        def menuEntry = new MenuDSL(entry_header : map[HEADER_FIELD])
        DslUtils.delegate(closure, menuEntry)

        menubar.addMenu(~menuEntry)
    }

    def component_reference() { null }

    def bitwiseNegate() { root }
}

