package com.lapots.game.journey.platform.resource.protocol

import com.lapots.game.journey.util.DslUtils

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = "with")
class GRLMessage {
    def requestType
    def requestGRL
    def content
}
