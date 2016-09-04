package com.lapots.game.journey.util

import com.lapots.game.journey.platform.CorePlatform;

class DomainUtils {

    static localizer = CorePlatform.managed["uiComponentStorage"].label_system_map

    static def label_to_param(label) {
        localizer[label]
    }

    static id_pair(custom_id) {
        if (custom_id) { [ "id " : custom_id ] }
        else { [ "id" : UUID.randomUUID().toString()] }
    }
}
