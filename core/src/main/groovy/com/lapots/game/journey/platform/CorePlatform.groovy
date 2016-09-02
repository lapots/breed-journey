package com.lapots.game.journey.platform

import org.springframework.context.support.ClassPathXmlApplicationContext

class CorePlatform {
    static {
        ClassPathXmlApplicationContext.metaClass.getAt = { String bean_name -> getBean(bean_name) }
    }

    static managed = new ClassPathXmlApplicationContext("application-context.xml")

    static class Constants {
        static final def LOCATION_RESOURCES = "location"
        static final def LOADER_PACKAGE = "com.lapots.game.journey.world.loader"
        // loading priority specified by order
        static final def SUPPORTED_EXTENSIONS = [ "location", "relation" ]
        static final LOADER_POSTFIX = "FileLoader"
        static final CORE_PATH = "world"
        static final SUB_RELATION = "children"
    }
}
