package com.lapots.game.journey.ui.dsl.window.wizard

import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
import com.lapots.game.journey.ui.dsl.basic.ButtonDSL
import com.lapots.game.journey.ui.dsl.window.WindowDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.EvaluationUtils

import java.awt.Button

/**
 * Represent window wizard component.
 * Wizard - is a special array of windows that allows
 * to move forward and backward. By default wizard
 * hide other windows. In wizard windows are the frames.
 *
 * The order of frames in DSL is the default wizard order.
 * Might allow indexing.
 * Basically
 *
 * <pre>
 *     wizard(title: 'Configure player') {
 *          withCloseButton true
 *          frame(title: 'Choose defaults') {
 *          }
 *          position {
 *              x 10
 *              y 10
 *          }
 *          layout {
 *              grid {
 *                  columns 1
 *              }
 *          }
 *     }
 * </pre>
 */
class WizardDSL implements CompositeTrait {

    def wizard = []
    def callback = [:]
    def index = 0
    def currentWindow
    //=============================DSL specifics==============================
    def call(map, closure) {
        id = "wizard-" + uuid()
        UiHelper.componentRegistry[(id)] = this
        DslUtils.delegate(closure, this)

        index = 0
    }

    def layout(closure) { callback << [ "layout" : closure ] }

    def position(closure) { callback << [ "position" : closure ] }

    def frame(map, closure) {
        def window = new WindowDSL()
        window.call(closure)

        addNextButton(window)

        wizard[index] = window
        index++
    }

    def frame(closure) { frame([:], closure) }

    def withCloseButton(closure) { callback << [ "withCloseButton" : closure ] }
    //=================================END=====================================

    def addNextButton(windowDsl) {
        def nextButton = new ButtonDSL()
        EvaluationUtils.evaluateWithBinding("button(label: 'Next') {}", [ "button" : nextButton])
        windowDsl.ids << nextButton.id
        nextButton.parentUid = windowDsl.id
        bindEvent(nextButton)

        windowDsl.appendChild(nextButton.getInnerComponent())

        println "window components: $windowDsl.ids"
    }

    def applyCallback(windowDsl) {
        callback.each { method, closure ->
            windowDsl."$method"(closure)
        }
    }

    def currentWindowState() { currentWindow.enumerateChildren() }

    def showWindow(index) {
        println "Attempt to get window by index: $index; windows: $wizard"
        if (index >= wizard.size()) {
            println "No next window found!"
            currentWindow = null
            this.index = 0
        }
        else {
            this.index = index
            def windowDsl = wizard[index]

            applyCallback(windowDsl)
            windowDsl.withPack(true)
            windowDsl.showWindow()

            currentWindow = windowDsl
        }
    }

    def closeCurrentWindow() {
        wizard[index].closeWindow()
    }

    def bindEvent(button) {
        def event = new NextWindowClick()
        println "wizard id: $id"
        event.boundWizardId = this.id
        event.currentWindowIndex = index
        button.getRawComponent().addListener(event)
    }

    @Override
    def enumerateChildren() { return null }

    @Override
    def appendChild(Object child) { return null }

    @Override
    def getInnerComponent() { return null }

    @Override
    def getRawComponent() { return null }
}
