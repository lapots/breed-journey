package com.lapots.game.journey.util

import com.lapots.game.journey.core.api.protocol.GRLMessage


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

    static createGetRequest(grl, headers) {
        new GRLMessage()
            .withRequestType("GET")
            .withRequestGRL(grl)
            .withHeaders(headers)
    }

    static createPostRequest(grl, content, headers) {
        new GRLMessage()
            .withRequestType("POST")
            .withContent(content)
            .withRequestGRL(grl)
            .withHeaders(headers)
    }
}
