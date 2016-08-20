package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.ui.UiConstants
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.FileProcessingUtils
import com.lapots.game.journey.util.ReflectionUtils

trait DynamicClosureTrait {

    def methodMissing(String name, args) {
        def dsl = FileProcessingUtils
                .createFileName(UiConstants.DSL_PACKAGE,
                        UiConstants.DSL_POSTFIX, name)
        def dsl_instance = ReflectionUtils.instantiate(dsl)
        dsl_instance.call(*args)

        this.component_reference().add(~dsl_instance)
    }
}
