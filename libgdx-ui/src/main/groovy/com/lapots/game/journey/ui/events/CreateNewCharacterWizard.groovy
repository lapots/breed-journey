package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.ui.dsl.api.event.IdentifiableEventTrait
import com.lapots.game.journey.ui.dsl.window.wizard.WizardDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.EvaluationUtils

/**
 * Handles click on [New Player] item in application menu.
 */
class CreateNewCharacterWizard extends InputListener implements IdentifiableEventTrait {

    static final NEW_CHARACTER_WIZARD_COMPONENT = "create_character_wizard"
    static final WIZARD = "wizard"

    @Override
    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def newCharacterWizard = UiHelper["ui:$NEW_CHARACTER_WIZARD_COMPONENT"]
        def wizardDSL = new WizardDSL()
        EvaluationUtils.evaluateWithBinding(newCharacterWizard, [(WIZARD) : wizardDSL ])

        wizardDSL.showWindow(0)
    }

}
