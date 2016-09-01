package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.GridTableBuilder
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.platform.UiPlatform;
import com.lapots.game.journey.util.DslUtils;
import java.lang.ref.SoftReference

//
class WindowDSL implements DynamicClosureTrait, IReferenced {

    private static final String HEADER_KEY = "title"

    def header
    def need_pack
    @Lazy VisWindow window = new VisWindow(header)
    @Lazy(soft=true) GridTableBuilder grid = new GridTableBuilder(1)

    def withCloseButton(closure) { !closure() ?: window.addCloseButton() }
    def allowMoving(closure) { window.setMovable(closure()) }
    def withPack(closure) { need_pack = closure() }

    // something is odd here
    def gridLayout(closure) { DslUtils.delegate(closure, this) }
    def columns(value) { setGrid(new GridTableBuilder(value)) }

    def call(closure) {
        call(title : '', closure)
    }

    def call(map, closure) {
        header = map[HEADER_KEY]
        UiPlatform.default_stage.addActor(window)
        TableUtils.setSpacingDefaults(window)
        window.columnDefaults(0).left()

        DslUtils.delegate(closure, this)

        window.add(grid.build())
        if (need_pack) {
            window.pack()
        }
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

    def bitwiseNegate() { window }
}
