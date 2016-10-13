package com.lapots.game.journey.util

/**
 * Temporary class as I separating UI from implementation.
 */
class ReflectionUtils {

    static instantiate(name) { Class.forName(name).newInstance() }

    static instantiateOne(packageList, clName) {
        def instance = null
        packageList.each { className ->
            try {
                instance = instantiate(className + "." + clName)
                if (instance != null) {
                    println "Found ${ className + "." + clName }"
                    return
                }
            } catch (ClassNotFoundException) {
                // generally if exception happens that it means that class not found
                // and we should proceed with iterations
            }
        }
        return instance
    }

    static instantiateOne(packageList, clName, postfix) {
        instantiateOne(packageList, FileProcessingUtils.createFileName(postfix, clName))
    }

}
