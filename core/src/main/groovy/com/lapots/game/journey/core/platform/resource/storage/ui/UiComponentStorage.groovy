package com.lapots.game.journey.core.platform.resource.storage.ui

import UiHelper;
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
        UiHelper["application.supported_extensions"].each { ext ->
            loaders[ext] = ReflectionUtils.instantiate(createFileName(ext))
        }
    }

    static {
        new File(UiHelper["application.component_path"]).eachFileRecurse { file ->
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
        FileProcessingUtils.createFileName(UiHelper["application.loader_package"],
                UiHelper["application.loader_postfix"], ext)
    }

}
