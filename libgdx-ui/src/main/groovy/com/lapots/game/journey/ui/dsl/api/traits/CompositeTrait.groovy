package com.lapots.game.journey.ui.dsl.api.traits

import com.lapots.game.journey.ui.dsl.api.ICompositeDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.ReflectionUtils

/**
 * Trait for components that can store other components
 * and return list of its ids.
 *
 * Additionally it provides an ability to resolve
 * DSL for other components via delegation.
 */
trait CompositeTrait implements ICompositeDSL {
    def ids = []


    def methodMissing(String name, args) {
        def dsl_instance = ReflectionUtils.instantiateOne(UiHelper["application.dsl_packages"], name,
                UiHelper["application.dsl_postfix"])
        dsl_instance.call(*args)

        dsl_instance.parentUid = this.id
        ids << dsl_instance.id

        component_reference().append(~dsl_instance)
    }
}
