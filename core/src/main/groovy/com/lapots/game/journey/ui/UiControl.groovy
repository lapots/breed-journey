package com.lapots.game.journey.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import com.lapots.game.journey.util.FileProcessingUtils
import com.lapots.game.journey.util.ReflectionUtils;;

class UiControl {
    static Stage default_stage
    static components = [:]

    static LOADERS = [:]

    static {
        print "Loading extensions loaders..."
        UiConstants.SUPPORTED_EXTENSIONS.each { ext ->
            LOADERS[ext] = ReflectionUtils.instantiate(createFileName(ext))
        }
        println "Done!"
    }

    static {
        print "Processing resources..."
        new File(UiConstants.COMPONENT_PATH).eachFileRecurse { file ->
            def loader = LOADERS[
                FileProcessingUtils.getFileExt(file)
            ]
            if (loader) { loader.load(file) }
        }
        println "Done!"
    }

    static def createFileName(ext) {
        FileProcessingUtils.createFileName(UiConstants.LOADER_PACKAGE,
                UiConstants.LOADER_POSTFIX, ext)
    }
}
