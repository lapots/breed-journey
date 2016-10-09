package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.ui.dsl.window.WindowDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.EvaluationUtils

/**
 * Handles click on [New Player] item in application menu.
 */
class CreateNewPlayerWindow extends InputListener {

    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def newCharacterWindow = UiHelper["components.new_character"]
        EvaluationUtils.evaluateWithBinding(newCharacterWindow, [ "window" : new WindowDSL() ])
    }
}
