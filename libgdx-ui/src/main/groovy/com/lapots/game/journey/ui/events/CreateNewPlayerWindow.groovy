package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.ui.dsl.api.event.IdentifiableEventTrait
import com.lapots.game.journey.ui.dsl.window.WindowDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.EvaluationUtils

/**
 * Handles click on [New Player] item in application menu.
 */
class CreateNewPlayerWindow extends InputListener implements IdentifiableEventTrait {

    static final NEW_CHARACTER_COMPONENT = UiHelper["components.new_character.name"]
    static final NEW_CHARACTER_ENTRY = UiHelper["components.new_character.entry"]

    @Override
    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def newCharacterWindow = UiHelper["ui:$NEW_CHARACTER_COMPONENT"]
        def windowDsl = new WindowDSL()
        EvaluationUtils.evaluateWithBinding(newCharacterWindow, [ (NEW_CHARACTER_ENTRY) : windowDsl ])
        windowDsl.showWindow()
    }
}

/**
 *
 *
 * Key moment there:
 *      ui: - file resources (raw .component data)
 *      prop: - properties from libgdx.config
 *      event: - event bank aka eventRegistry
 *      runtime: - object bank aka componentRegistry
 *
 */
