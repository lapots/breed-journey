package com.lapots.game.journey.world

import com.lapots.game.journey.util.FileProcessingUtils;
import com.lapots.game.journey.util.ReflectionUtils;

class CoreControl {
    static def resources = [:]

    static LOADERS = [:]

    static {
        print "Initializing resource paths..."
        resources[CoreConstants.LOCATION_RESOURCES] = [:]
        println "Done!"
    }

    static {
        print "Loading CORE extensions loaders..."
        CoreConstants.SUPPORTED_EXTENSIONS.each { ext ->
            LOADERS[ext] = ReflectionUtils.instantiate(createFileName(ext))
        }
        println "Done!"
    }

    static {
        print "Processing CORE resources..."
        // priority
        def file_path = new File(CoreConstants.CORE_PATH)
        CoreConstants.SUPPORTED_EXTENSIONS.each { ext ->
            file_path.eachFileRecurse { file ->
                def file_ext = FileProcessingUtils.getFileExt(file)
                if (ext == file_ext) {
                    def loader = LOADERS[ext]
                    if (loader) {
                        loader.load(file)
                    }
                }
            }
        }
        println "Done!"
    }

    static def createFileName(ext) {
        FileProcessingUtils.createFileName(CoreConstants.LOADER_PACKAGE,
                CoreConstants.LOADER_POSTFIX, ext)
    }
}
