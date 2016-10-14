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

    static resourceLoader(file) {
        def classLoader = Thread.currentThread().getContextClassLoader()
        new File(classLoader.getResource(file).getFile())
    }

    static jsonResourceLoader(file) {
        def classLoader = Thread.currentThread().getContextClassLoader()
        new JsonSlurper().parse(new File(classLoader.getResource(file).getFile()))
    }
    static Stage mainStage

    // load .config file
    static jsonResource
    static {
        jsonResource = jsonResourceLoader("libgdx.config")
    }

    // load Ui resource files (.component etc.)
    // represent static index
    static componentResources = [:]
    static {
        def assets = resourceLoader(jsonResource.application.assets_path)
        def supportedExtensions = jsonResource.application.supported_extensions as List
        assets.eachFileRecurse { file ->
            println "$file"
            if (FileProcessingUtils.getFileExt(file) in supportedExtensions) {
                def resourceKey = FileProcessingUtils.getFileName(file)
                componentResources[(resourceKey)] = file.text
            }
        }
    }

    // represent dynamic index
    static componentRegistry = [:]
    // special registry to bind events to components
    // for now - one component bound to one id
    static eventRegistry = [:]
    static Table root

    // some adjustments to existing classes
    static { Actor.metaClass.parentUid = "" }

    static getAt(name) {
        // imitate routing
        if (name.indexOf(":") != -1) {
            def subRoute = name[0..name.indexOf(":")]
            def res = name[name.indexOf(":") + 1..name.length() - 1]
            switch (subRoute) {
                case "ui:": return componentResources[(res)]
                case "prop:": return jsonPathSolver(jsonResource, name)
                case "event:": return eventRegistry[(res)]
                case "runtime:": return componentRegistry[(res)]
            }
        } else { jsonPathSolver(jsonResource, name) }
    }

    static jsonPathSolver(json, path) {
        path += '.' // at least one additional loop before quiting [while]
        while (path.indexOf('.') != -1) {
            def subpathIndex = path.indexOf('.')
            def subpath = path[0..subpathIndex - 1]
            if (subpathIndex + 1 < path.length()) { path = path[subpathIndex + 1..path.length() - 1] }
            else { path -= '.' }
            json = json."$subpath"
        }
        json
    }
}
