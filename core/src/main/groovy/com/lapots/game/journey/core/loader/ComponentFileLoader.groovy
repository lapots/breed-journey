package com.lapots.game.journey.core.loader

import com.lapots.game.journey.ui.UiControl;
import com.lapots.game.journey.util.FileProcessingUtils;

class ComponentFileLoader implements ILoader {

    // we are not ready to evaluate .component yet
    def load(file) {
        UiControl.components [
            FileProcessingUtils.getFileName(file)
        ] = file.text
    }

}
