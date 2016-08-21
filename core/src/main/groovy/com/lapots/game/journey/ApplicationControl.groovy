package com.lapots.game.journey

import com.lapots.game.journey.world.CoreConstants
import com.lapots.game.journey.world.CoreControl;;

class ApplicationControl {

    static def class_registry = [:]

    static {
        class_registry["CoreConstants"] = CoreConstants.class
        class_registry["CoreControl"] = CoreControl.class
    }
}
