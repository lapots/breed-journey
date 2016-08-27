package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.ui.UiControl;
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils;

class EditPlayerInformation extends InputListener {

    private static final String PERSON_EDITOR_COMPONENT = "person_editor"

    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def code = UiControl.components[PERSON_EDITOR_COMPONENT]
        EvaluationUtils.evaluateWithBinding(code, [ "window" : new WindowDSL() ])
    }
}
