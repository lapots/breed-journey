package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.util.DslUtils;
import com.lapots.game.journey.util.ReflectionUtils;

trait DynamicClosureTrait {

    def methodMissing(String name, args) {
        def dsl = FileProcessingUtils
                .createFileName(UiConstants.LOADER_PACKAGE,
                        UiConstants.LOADER_POSTFIX, name)
        def dsl_instance = ReflectionUtils.instantiate(dsl)
        DslUtils.delegate(args[0], dsl_instance)
    }
}
