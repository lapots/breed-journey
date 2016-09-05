package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.platform.ResourcePlatform;
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils
import com.lapots.game.journey.util.GrlUtils;;;

class CreateNewPlayerWindow extends InputListener {

    private static final String CREATE_CHARACTER_WINDOW = "create_character_window"

    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def code = ResourcePlatform <<
                GrlUtils.createGetRequest("ui://$CREATE_CHARACTER_WINDOW", null)
        EvaluationUtils.evaluateWithBinding(code, [ "window" : new WindowDSL() ])
    }
}
