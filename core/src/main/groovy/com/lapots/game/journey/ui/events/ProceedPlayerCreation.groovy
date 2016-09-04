package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.ui.dsl.CompositeTrait

class ProceedPlayerCreation extends ChangeListener {

    @Override
    public void changed (ChangeEvent event, Actor actor) {
        if (actor.parentUid) {
            println "${ CorePlatform.managed["uiComponentStorage"].registered[actor.parentUid].collect_data() }"
        }
    }
}
