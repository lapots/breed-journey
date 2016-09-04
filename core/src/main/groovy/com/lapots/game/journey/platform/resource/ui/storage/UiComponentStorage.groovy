package com.lapots.game.journey.platform.resource.ui.storage

import com.lapots.game.journey.platform.UiPlatform;
import com.lapots.game.journey.util.FileProcessingUtils;
import com.lapots.game.journey.util.ReflectionUtils;;

import org.springframework.stereotype.Component

@Component
class UiComponentStorage {

    static def components = [:]
    static def registered = [:]
    static def loaders = [:]

    // move to external system
    static def label_system_map = [
            "Enter name" : "name",
            "Choose gender" : "gender",
            "Choose race" : "race"
    ]

    static {
        UiPlatform.Constants.SUPPORTED_EXTENSIONS.each { ext ->
            loaders[ext] = ReflectionUtils.instantiate(createFileName(ext))
        }
    }

    static {
        new File(UiPlatform.Constants.COMPONENT_PATH).eachFileRecurse { file ->
            def loader = loaders [
                FileProcessingUtils.getFileExt(file)
            ]
            if (loader) { loader.load(file) }
        }
    }

    def read(key) {
        components[key]
    }

    private static def createFileName(ext) {
        FileProcessingUtils.createFileName(UiPlatform.Constants.LOADER_PACKAGE,
                UiPlatform.Constants.LOADER_POSTFIX, ext)
    }

}
