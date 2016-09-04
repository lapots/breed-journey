package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.GridTableBuilder
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.platform.UiPlatform;
import com.lapots.game.journey.util.DslUtils;
import java.lang.ref.SoftReference

//
class WindowDSL implements DynamicClosureTrait, CompositeTrait, IReferenced, IdentifiableTrait {

    private static final String HEADER_KEY = "title"

    def header
    def need_pack
    @Lazy VisWindow window = new VisWindow(header)
    GridTableBuilder grid = new GridTableBuilder(1)

    def withCloseButton(closure) { !closure() ?: window.addCloseButton() }
    def allowMoving(closure) { window.setMovable(closure()) }
    def withPack(closure) { need_pack = closure() }

    // something is odd here
    def gridLayout(closure) { DslUtils.delegate(closure, this) }
    def columns(value) {
        grid = new GridTableBuilder(value)
    }

    def call(closure) {
        call(title : '', closure)
    }

    def call(map, closure) {
        id = uuid()
        println "Window id : $id"
        header = map[HEADER_KEY]
        UiPlatform.default_stage.addActor(window)
        TableUtils.setSpacingDefaults(window)
        window.columnDefaults(0).left()

        DslUtils.delegate(closure, this)

        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        window.add(grid.build())
        if (need_pack) {
            window.pack()
        }
    }

    def center(closure) {
        !closure() ?: window.centerWindow()
    }

    def size(closure) {
        def w_size = new WindowSize()
        DslUtils.delegate(closure, w_size)
    }

    def position(closure) {
        def w_position = new WindowPosition()
        DslUtils.delegate(closure, w_position)
    }

    class WindowSize {
        def width(w) { window.setWidth(w) }
        def height(h) { window.setHeight(h) }
    }

    class WindowPosition {
        def x(x) { window.setX(x) }
        def y(y) { window.setY(y) }
    }

    def component_reference() { grid }
    def identifiable_instance() { window }
    def bitwiseNegate() { window }
}
