package com.lapots.game.journey.core.platform

import org.springframework.context.support.ClassPathXmlApplicationContext

class CorePlatform extends ManagedPlatform {

    static class Constants {
        static final def LOCATION_RESOURCES = "location"
        static final def LOADER_PACKAGE = "com.lapots.game.journey.core.loader"
        // loading priority specified by order
        static final def SUPPORTED_EXTENSIONS = [ "location", "relation" ]
        static final LOADER_POSTFIX = "FileLoader"
        static final CORE_PATH = "world"
        static final SUB_RELATION = "children"
    }
}
