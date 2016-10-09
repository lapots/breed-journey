package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.ui.helper.UiHelper;
import com.lapots.game.journey.ui.dsl.traits.CompositeTrait
import com.lapots.game.journey.ui.dsl.traits.IdentifiableTrait
import com.lapots.game.journey.util.FileProcessingUtils
import com.lapots.game.journey.util.ReflectionUtils;;;

class RootDSL implements IdentifiableTrait, CompositeTrait {

    def root = UiHelper.root

    RootDSL() {
        // ???
        id = "MAIN"
    }

    def methodMissing(String name, args) {
        def dsl = FileProcessingUtils
                .createFileName(UiHelper["application.dsl_package"],
                        UiHelper["application.dsl_postfix"], name)
        def dsl_instance = ReflectionUtils.instantiate(dsl)
        dsl_instance.call(*args)

        if (dsl_instance in IdentifiableTrait) {
            dsl_instance.identifiable_instance().parentUid = this.id
            if (this in CompositeTrait) {
                ids << dsl_instance.id
            }
        }

        root.add(~dsl_instance).row()
    }

    def identifiable_instance() {
        root
    }
}
