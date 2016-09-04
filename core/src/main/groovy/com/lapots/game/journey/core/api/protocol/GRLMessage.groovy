package com.lapots.game.journey.core.api.protocol

import com.lapots.game.journey.util.DslUtils

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = "with")
class GRLMessage {
    def requestType
    def requestGRL
    def content
    def headers

    String toString() {
"""
GRL request ->
request grl     : $requestGRL
request headers : $headers
request type    : $requestType
request content : $content
"""
    }
}
