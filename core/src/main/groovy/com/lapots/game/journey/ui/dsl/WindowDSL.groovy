package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.GridTableBuilder
import com.kotcrab.vis.ui.building.utilities.Padding
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.core.api.ICloseable
import com.lapots.game.journey.core.api.IReferenced
import com.lapots.game.journey.core.platform.CorePlatform;
import com.lapots.game.journey.core.platform.UiPlatform;
import com.lapots.game.journey.ui.dsl.traits.CompositeTrait
import com.lapots.game.journey.ui.dsl.traits.DynamicClosureTrait
import com.lapots.game.journey.ui.dsl.traits.IdentifiableTrait
import com.lapots.game.journey.util.DslUtils;
import java.lang.ref.SoftReference

class WindowDSL implements 
                DynamicClosureTrait,
                CompositeTrait,
                IReferenced,
                IdentifiableTrait,
                ICloseable
{

    private static final String HEADER_KEY = "title"

    final Padding padding = new Padding(2, 3);

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
        UiPlatform.default_stage.addActor(window)

        TableUtils.setSpacingDefaults(this.window);
        window.defaults().padRight(5);
        window.defaults().padLeft(5);
        window.columnDefaults(0).left();

        DslUtils.delegate(closure, this)

        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        window.add(grid.build())
        if (need_pack) {
            window.pack()
        }

        justify_offset()
    }

    def justify_offset() {
        window.setX((int) (window.getX() - window.getWidth() / 4))
        window.setY((int) (window.getY() + window.getHeight() / 2))
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
