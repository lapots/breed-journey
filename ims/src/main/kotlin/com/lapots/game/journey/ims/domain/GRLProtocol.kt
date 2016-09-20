package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.GRLException
import com.lapots.game.journey.ims.IMSException

class GRLProtocol {
    enum class GRLMethod {
        PUT, POST, GET
    }

    companion object {
        val supportedHeaders = {
            "destination" to true
            "sender" to true
            "receiver" to true
            "contentType" to false
        } as Map<String, Boolean>

        fun checkHeaderConsistency(messageHeaders : List<String>) {
            if (!messageHeaders.containsAll(supportedHeaders.filterValues { it == true }.keys)) {
                throw GRLException("Missing required headers!")
            }
        }
    }
}