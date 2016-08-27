package com.lapots.game.journey.util

class GrlUtils {

    static getRouteResolver(path) {
        path[0..path.indexOf("://") + 2]
    }

    static getRoutePath(path) {
        path[path.indexOf("://") + 2..path.length() - 1]
    }

    static getRouteKey(path) {
        path[path.lastIndexOf("/") + 1..path.length() - 1]
    }
}
