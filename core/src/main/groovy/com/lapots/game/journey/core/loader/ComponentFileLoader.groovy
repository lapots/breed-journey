package com.lapots.game.journey.core.loader

import com.lapots.game.journey.core.api.ILoader
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.platform.resource.ui.storage.UiComponentStorage;
import com.lapots.game.journey.util.FileProcessingUtils;

class ComponentFileLoader implements ILoader {

    // we are not ready to evaluate .component yet
    def load(file) {
        UiComponentStorage.components [
            FileProcessingUtils.getFileName(file)
        ] = file.text
    }

}
