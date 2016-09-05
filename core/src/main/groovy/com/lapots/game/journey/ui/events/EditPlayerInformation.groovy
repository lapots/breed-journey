package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.platform.ResourcePlatform;
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils
import com.lapots.game.journey.util.GrlUtils;;

class EditPlayerInformation extends InputListener {

    private static final String PERSON_EDITOR_COMPONENT = "person_editor"

    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def code = ResourcePlatform <<
                GrlUtils.createGetRequest("ui://$PERSON_EDITOR_COMPONENT", null)
        EvaluationUtils.evaluateWithBinding(code, [ "window" : new WindowDSL() ])
    }
}
