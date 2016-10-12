package com.lapots.game.journey.ui.dsl.window

import com.kotcrab.vis.ui.widget.VisWindow
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
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

    // in case of not initialize grid before adding components
    def componentQueue = []

    //=====================DSL specifics=================================
    def call(closure) { call(title : '', closure) }

    def call(map, closure) {
        id = uuid()
        window = new VisWindow(map[UiHelper["dsl.config.window_header_key"]])
        UiHelper.mainStage.addActor(window)
        WindowConfig.windowTableConfig(window)

        DslUtils.delegate(closure, this)

        if (needPack) { window.pack()}

        WindowPosition.offsetWindow(window)
        UiHelper.componentRegistry[(id)] = this
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

        def gridComponent = grid.build()

        if (!componentQueue) {
            componentQueue.each { component -> gridComponent.append(component) }
            componentQueue.clear()
        }
        window.add(gridComponent)
    }
    //=============================END====================================

    // method is protected by I do not care
    def closeWindow() { window.close() }

    @Override
    def enumerateChildren() {
        this.ids.each {}
    }

    @Override
    def appendChild(Object child) {
        if (!grid) { componentQueue << child }
        else { gridComponent.append(child) }
    }

    @Override
    def getInnerComponent() { return grid }

    @Override
    def getRawComponent() { return window }
}
