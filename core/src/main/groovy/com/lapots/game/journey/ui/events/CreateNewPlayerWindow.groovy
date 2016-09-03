package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils;;

class CreateNewPlayerWindow extends InputListener {

    private static final String CREATE_CHARACTER_WINDOW = "create_character_window"

    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def code = CorePlatform.managed["uiComponentStorage"].components[CREATE_CHARACTER_WINDOW]
        EvaluationUtils.evaluateWithBinding(code, [ "window" : new WindowDSL() ])
    }
}
