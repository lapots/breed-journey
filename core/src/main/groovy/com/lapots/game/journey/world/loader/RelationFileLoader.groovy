package com.lapots.game.journey.world.loader

import com.lapots.game.journey.core.loader.ILoader
import com.lapots.game.journey.util.FileProcessingUtils;
import com.lapots.game.journey.world.CoreConstants;
import com.lapots.game.journey.world.CoreControl;

class RelationFileLoader implements ILoader {

    def load(file) {
        FileProcessingUtils.readAsJson(file).each { parent, children ->
            CoreControl.resources[CoreConstants.LOCATION_RESOURCES][parent][CoreConstants.SUB_RELATION] = children
        }
    }
}
