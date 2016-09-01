package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.utilities.CellWidget
import com.lapots.game.journey.platform.UiPlatform;
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.FileProcessingUtils
import com.lapots.game.journey.util.ReflectionUtils

trait DynamicClosureTrait {

    def methodMissing(String name, args) {
        def dsl = FileProcessingUtils
                .createFileName(UiPlatform.Constants.DSL_PACKAGE,
                        UiPlatform.Constants.DSL_POSTFIX, name)
        def dsl_instance = ReflectionUtils.instantiate(dsl)
        dsl_instance.call(*args)

        component_reference().append(~dsl_instance)
    }
}
