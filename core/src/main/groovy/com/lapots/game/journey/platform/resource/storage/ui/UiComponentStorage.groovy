package com.lapots.game.journey.platform.resource.storage.ui

import com.lapots.game.journey.ui.UiConstants
import com.lapots.game.journey.util.FileProcessingUtils;
import com.lapots.game.journey.util.ReflectionUtils;;

@Singleton
class UiComponentStorage {

    static def components = [:]
    static def loaders = [:];

    static {
        println "Attempt to initialize UI storage"
        UiConstants.SUPPORTED_EXTENSIONS.each { ext ->
            loaders[ext] = ReflectionUtils.instantiate(createFileName(ext))
        }
    }

    static {
        new File(UiConstants.COMPONENT_PATH).eachFileRecurse { file ->
            def loader = loaders [
                FileProcessingUtils.getFileExt(file)
            ]
            if (loader) { loader.load(file) }
        }
        println "Done!"
    }

    def read(key) {
        components[key]
    }

    private static def createFileName(ext) {
        FileProcessingUtils.createFileName(UiConstants.LOADER_PACKAGE,
                UiConstants.LOADER_POSTFIX, ext)
    }
}
