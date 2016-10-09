package com.lapots.game.journey.util

class ReflectionUtils {

    static instantiate(name) {
        Class.forName(name).newInstance()
    }

    static instantiateOne(packageList, clName) {
        def instance = null
        packageList.each { className ->
            try {
                instance = instantiate(className + "." + clName).newInstance()
            } catch (Exception exc) {
                // means that class in that package is not found
                println exc.message()
            }
        }
        return instance
    }
}
