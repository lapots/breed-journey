package com.lapots.game.journey.ui.loader

import com.lapots.game.journey.core.loader.ILoader
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.platform.resource.storage.ui.UiComponentStorage;
import com.lapots.game.journey.util.FileProcessingUtils;

class ComponentFileLoader implements ILoader {

    // we are not ready to evaluate .component yet
    def load(file) {
        UiComponentStorage.components [
            FileProcessingUtils.getFileName(file)
        ] = file.text
    }

}
