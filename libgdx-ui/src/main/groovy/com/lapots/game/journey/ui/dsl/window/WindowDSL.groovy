package com.lapots.game.journey.ui.dsl.window

import com.kotcrab.vis.ui.building.GridTableBuilder
import com.kotcrab.vis.ui.building.utilities.Padding
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.ui.helper.UiHelper;
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait

import com.lapots.game.journey.ui.dsl.api.traits.IdentifiableTrait
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.EvaluationUtils

class WindowDSL implements
                CompositeTrait,
                IdentifiableTrait
{

    private static final String HEADER_KEY = UiHelper["dsl.config.window_header_key"]

    def paddingHorizontal = UiHelper["component.config.padding.horizontal"] as int
    def paddingVertical = UiHelper["component.config.padding.vertical"] as int
    final Padding padding = new Padding(paddingHorizontal, paddingVertical);

    def header
    def need_pack
    @Lazy VisWindow window = new VisWindow(header)
    GridTableBuilder grid = new GridTableBuilder(padding, 1)

    def withCloseButton(closure) { !closure() ?: window.addCloseButton() }
    def allowMoving(closure) { window.setMovable(closure()) }
    def withPack(closure) { need_pack = closure() }

    def gridLayout(closure) { DslUtils.delegate(closure, this) }
    def columns(value) {
        grid = new GridTableBuilder(padding, value)
    }

    def call(closure) {
        call(title : '', closure)
    }

    def call(map, closure) {
        id = uuid()
        header = map[HEADER_KEY]
        UiHelper.default_stage.addActor(window)

        // should I move it to external ?
        TableUtils.setSpacingDefaults(this.window);
        window.defaults().padRight(5);
        window.defaults().padLeft(5);
        window.columnDefaults(0).left();

        DslUtils.delegate(closure, this)

        window.add(grid.build())
        if (need_pack) {
            window.pack()
        }

        justify_offset()
    }

    def justify_offset() {
        def x_offset = UiHelper["component.config.window_center_offset"]
        def x_offset_val = EvaluationUtils.evaluateWithBinding(x_offset, window) as int

        def y_offset = UiHelper["component.config.window_center_offset"]
        def y_offset_val = EvaluationUtils.evaluateWithBinding(y_offset, window) as int

        window.setX(x_offset_val)
        window.setY(y_offset_val)
    }

    def close() {
        window.close()
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
