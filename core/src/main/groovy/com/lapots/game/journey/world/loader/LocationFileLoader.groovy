package com.lapots.game.journey.world.loader

import com.lapots.game.journey.core.loader.ILoader
import com.lapots.game.journey.util.FileProcessingUtils;
import com.lapots.game.journey.world.CoreConstants;
import com.lapots.game.journey.world.CoreControl;

class LocationFileLoader implements ILoader {

    def load(file) {
        def resource = FileProcessingUtils.readAsJson(file)
        CoreControl.resources[CoreConstants.LOCATION_RESOURCES][
            resource.id
        ] = resource
    }
}
