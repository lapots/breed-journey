package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lapots.game.journey.platform.resource.storage.ui.UiComponentStorage;
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils;;

class WorldInformationWindow extends InputListener {

    private static final String WORLD_INFO_COMPONENT = "world_info"

    boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        def code = UiComponentStorage.instance.components[WORLD_INFO_COMPONENT]
        EvaluationUtils.evaluateWithBinding(code, [ "window" : new WindowDSL() ])
    }
}
