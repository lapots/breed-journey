package com.lapots.game.journey.ui.dsl.window.wizard

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.ui.dsl.api.event.IdentifiableEventTrait
import com.lapots.game.journey.ui.helper.UiHelper

/**
 * Event for click button next for wizard.
 */
class NextWindowClick extends InputListener implements IdentifiableEventTrait {

    def currentWindowIndex = -1
    def boundWizardId

    @Override
    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def wizard = UiHelper["runtime:$boundWizardId"]
        // close just in case
        wizard.closeCurrentWindow()
        wizard.showWindow(++currentWindowIndex)
    }

}
