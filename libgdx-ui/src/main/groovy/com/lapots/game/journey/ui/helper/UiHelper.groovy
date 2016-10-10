package com.lapots.game.journey.ui.helper

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.lapots.game.journey.util.FileProcessingUtils
import groovy.json.JsonSlurper

/**
 * Special helper class that stores UI configuration
 * and other globals.
 *
 * Presumable might be used as gate to outer subsystems.
 */
class UiHelper {
    static Stage default_stage

    // load .config file
    static jsonResource
    static {
        def classLoader = Thread.currentThread().getContextClassLoader()
        jsonResource = new JsonSlurper().parse(
                new File(classLoader.getResource("libgdx.config").getFile())
        )
    }

    // load Ui resource files (.component etc.)
    static componentResources = [:]
    static {
        def assets = Gdx.files.internal(jsonResource.application.assets_path).file()
        def supportedExtensions = jsonResource.application.supported_extensions as List
        assets.eachFileRecurse { file ->
            if (FileProcessingUtils.getFileExt(file) in supportedExtensions) {
                def resourceKey = FileProcessingUtils.getFileName(file)
                componentResources[resourceKey] = file.text
            }
        }
    }

    // some adjustments to existing classes
    static {
        Stage.metaClass.add = { component -> addActor(component) }
        Actor.metaClass.parentUid = ""
    }

    static Table root = new Table()
    static {
        root.setFillParent(true)
    }

    static getAt(name) {
        // imitate routing
        if (name.indexOf(":") != -1) {
            def subRoute = name[0..name.indexOf(":")]
            def res = name[name.indexOf(":")..name.length() - 1]
            switch (subRoute) {
                case "ui:":
                    return componentResources[res]
                case "prop:":
                    return jsonResource."$res"
            }
        } else {
            jsonResource."$name"
        }
    }
}
