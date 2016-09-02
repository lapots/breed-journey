package com.lapots.game.journey.platform.core.protocol

import com.lapots.game.journey.util.DslUtils

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = "with")
class GRLMessage {
    def requestType
    def requestGRL
    def content
    def headers
}
