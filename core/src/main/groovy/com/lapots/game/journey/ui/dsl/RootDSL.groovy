package com.lapots.game.journey.ui.dsl

import com.lapots.game.journey.platform.UiPlatform
import com.lapots.game.journey.util.FileProcessingUtils
import com.lapots.game.journey.util.ReflectionUtils;;;

class RootDSL implements IdentifiableTrait, CompositeTrait {

    def root = UiPlatform.root

    RootDSL() {
        id = "MAIN"
    }

    def methodMissing(String name, args) {
        def dsl = FileProcessingUtils
                .createFileName(UiPlatform.Constants.DSL_PACKAGE,
                        UiPlatform.Constants.DSL_POSTFIX, name)
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
