package com.lapots.game.journey.ui.dsl.traits

import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.FileProcessingUtils
import com.lapots.game.journey.util.ReflectionUtils


// Trait for components that allow to create components inside
trait DynamicClosureTrait {

    def methodMissing(String name, args) {
        def dsl = FileProcessingUtils
                .createFileName(UiHelper["application.dsl_package"],
                        UiHelper["application.dsl_postfix"], name)
        def dsl_instance = ReflectionUtils.instantiate(dsl)
        dsl_instance.call(*args)

        // provide id reference to object
        if (dsl_instance in IdentifiableTrait) {
            dsl_instance.identifiable_instance().parentUid = this.id
            if (this in CompositeTrait) {
                ids << dsl_instance.id
            }
        }

        component_reference().append(~dsl_instance)
    }
}
