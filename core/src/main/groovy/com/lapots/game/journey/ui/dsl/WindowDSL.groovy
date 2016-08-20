package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.ui.UiControl;
import com.lapots.game.journey.util.DslUtils;

class WindowDSL implements DynamicClosureTrait, IReferenced {

    private static final String HEADER_KEY = "title"

    def header
    @Lazy VisWindow window = new VisWindow(header)

    def withCloseButton(closure) { !closure() ?: window.addCloseButton() }

    def call(map, closure) {
        header = map[HEADER_KEY]
        UiControl.default_stage.addActor(window)

        DslUtils.delegate(closure, this)
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

    def component_reference() { window }

    def bitwiseNegate() { window }
}
