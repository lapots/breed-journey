package com.lapots.game.journey.ui.dsl.api.traits

import com.lapots.game.journey.ui.dsl.api.ICompositeDSL
import com.lapots.game.journey.ui.exception.UiDSLException
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.FileProcessingUtils
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
        def dslInstance = ReflectionUtils.instantiateOne(UiHelper["application.packages.dsl_packages"], name,
                UiHelper["application.dsl_postfix"])
        if (null == dslInstance) { throw new UiDSLException("Unable to instantiate instance: $name -> $args") }
        else {
            dslInstance.call(*args)

            dslInstance.parentUid = this.id
            ids << dslInstance.id

            this.appendChild(dslInstance.getInnerComponent())
        }
    }
}
