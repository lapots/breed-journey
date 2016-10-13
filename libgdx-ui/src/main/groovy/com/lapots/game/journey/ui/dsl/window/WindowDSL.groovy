package com.lapots.game.journey.ui.dsl.window

import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
import com.lapots.game.journey.ui.dsl.api.traits.IdentifiableTrait
import com.lapots.game.journey.ui.helper.UiHelper;

import com.lapots.game.journey.util.DslUtils

/**
 * Represent window component.
 * Basically
 *
 * <pre>
 *      window(title : "Window title") {
 *             withCloseButton true
 *             withPack true
 *             withMove true
 *             withCenterPosition true
 *             size {
 *                 width 100
 *                 height 100
 *             }
 *             position {
 *                 x 10
 *                 y 10
 *             }
 *             layout {
 *                 grid {
 *                     columns 10
 *                 }
 *             }
 *      }
 * </pre>
 *
 * Almost every frame in the application is backed up
 * by window.
 */
class WindowDSL implements CompositeTrait {

    // pack supposed to be the last action so
    // there is a need in having dedicated variable
    def needPack

    def gridComponent
    VisWindow window
    //=====================DSL specifics=================================
    def call(closure) { call(title : '', closure) }

    def call(map, closure) {
        // do something with prefixes
        id = "window-" + uuid()
        window = new VisWindow(map[UiHelper["dsl.config.window_header_key"]])
        UiHelper.componentRegistry[(id)] = this
        WindowConfig.windowTableConfig(window)
        DslUtils.delegate(closure, this)
    }

    def withCloseButton(needButton) { !needButton ?: window.addCloseButton() }

    // pack should happen as the last
    def withPack(needPack) { this.needPack = needPack }

    def withMove(moveAllowed) { window.setMovable(moveAllowed) }

    def size(closure) {
        def windowSize = new WindowSize(referencedWindow: window)
        DslUtils.delegate(closure, windowSize)
    }

    def position(closure) {
        def windowPosition = new WindowPosition(referencedWindow: window)
        DslUtils.delegate(closure, windowPosition)
    }

    def withCenterPosition(needCenter) { !needCenter ?: window.centerWindow() }

    def layout(closure) { DslUtils.delegate(closure, this) }

    def grid(closure) {
        def grid = new GridLayout()
        DslUtils.delegate(closure, grid)

        gridComponent = grid.build()
    }
    //=============================END====================================

    def showWindow() {
        // WindowPosition.offsetWindow(window)
        UiHelper.mainStage.addActor(window)

        window.add(gridComponent.build())
        if (needPack) { window.pack()}

    }

    // method is protected by I do not care
    def closeWindow() { window.close() }

    @Override
    def enumerateChildren() {
        this.ids.each {}
    }

    @Override
    def appendChild(Object child) {
        //if (!gridComponent) { componentQueue << child }
        gridComponent.append(child)
    }

    @Override
    def getInnerComponent() { return gridComponent }

    @Override
    def getRawComponent() { return window }
}
