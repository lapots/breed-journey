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

    def wizard = [:]
    def callback = [:]
    def index = 0
    def currentWindow
    //=============================DSL specifics==============================
    def call(map, closure) {
        id = "wizard-" + uuid()
        UiHelper.componentRegistry[(id)] = this

        DslUtils.delegate(closure, this)

        index = 0
        showWindow(index)
    }

    def layout(closure) { callback << [ "layout" : closure ] }

    def position(closure) { callback << [ "position" : closure ] }

    def frame(closure) {
        def window = new WindowDSL()
        window.call(closure)

        addNextButton(window)

        wizard[index] = window
        index++
    }
    //=================================END=====================================

    def addNextButton(windowDsl) {
        def nextButton = new ButtonDSL()
        EvaluationUtils.evaluateWithBinding("button(label: 'Next') {}", [ "button" : nextButton])
        bindEvent(nextButton)

        applyCallback(windowDsl)
        windowDsl.appendChild(nextButton)
    }

    def applyCallback(windowDsl) {
        callback.each { method, closure ->
            windowDsl."$method"(closure)
        }
    }

    def showWindow(index) {
        this.index = index
        def windowDsl = wizard[index]
        windowDsl.showWindow()
    }

    def closeCurrentWindow() {
        wizard[index].getRawComponent().fadeIn()
    }

    def bindEvent(button) {
        def event = new NextWindowClick()
        event.boundWizardId = button.id
        button.addListener(event)
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
