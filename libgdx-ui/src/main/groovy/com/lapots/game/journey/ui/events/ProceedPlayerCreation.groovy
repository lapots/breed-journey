package com.lapots.game.journey.ui.events

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent
import com.lapots.game.journey.core.api.ICloseable
import com.lapots.game.journey.core.platform.CorePlatform
import com.lapots.game.journey.core.platform.ResourcePlatform;
import com.lapots.game.journey.util.DomainUtils
import com.lapots.game.journey.util.GrlUtils;

import groovy.json.JsonOutput;;;

// Creates player instance
class ProceedPlayerCreation extends ChangeListener {

    private getComponent(id) {
        CorePlatform.managed["uiComponentStorage"].registered[id]
    }

    @Override
    public void changed (ChangeEvent event, Actor actor) {
        if (actor.parentUid) {
            // GrlUtils.createGetRequest("ui://registered/$id", null)
            def component = CorePlatform.managed["uiComponentStorage"].registered[actor.parentUid]
            // GrlUtils.createGetRequest("ui://translation, null)
            def label_map = CorePlatform.managed["uiComponentStorage"].label_system_map
            def param_map = [:]
            component.ids.each {
                def cmp = getComponent(it)
                if (cmp in ValueReferencedTrait) {
                    param_map << cmp.getValue()
                }
            }
            param_map = param_map.collectEntries { k, v ->
                [ (DomainUtils.label_to_param(k)) : v ]
            }
            def idp = DomainUtils.id_pair()
            param_map << idp
            ResourcePlatform >>
                    GrlUtils.createPostRequest("redis://$idp.id", JsonOutput.toJson(param_map), null)
            def result = ResourcePlatform <<
                    GrlUtils.createGetRequest("redis://$idp.id", [ "expected type" : "java.lang.String"])
            if (component in ICloseable) {
                component.close()
            }
        }
    }
}