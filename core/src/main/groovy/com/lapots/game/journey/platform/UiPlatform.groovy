package com.lapots.game.journey.platform

import com.badlogic.gdx.scenes.scene2d.Stage

class UiPlatform extends ManagedPlatform {

    static Stage default_stage
    // replace with configurable
    static class Constants {
        static final EVENT_PACKAGE = "com.lapots.game.journey.ui.events"
        static final COMPONENT_PATH = "components"
        static final DSL_PACKAGE = "com.lapots.game.journey.ui.dsl"
        static final DSL_POSTFIX = "DSL"
        static final LOADER_PACKAGE = "com.lapots.game.journey.core.loader"
        static final LOADER_POSTFIX = "FileLoader"
        static final SUPPORTED_EXTENSIONS = [ "component" ]
    }
}
