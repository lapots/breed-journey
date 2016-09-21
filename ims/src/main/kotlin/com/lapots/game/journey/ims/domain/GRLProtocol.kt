package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.GRLException
import com.lapots.game.journey.ims.IMSException

class GRLProtocol {
    enum class GRLMethod {
        PUT, POST, GET
    }

    companion object {
        val supportedHeaders = mapOf<String, Boolean>(
            "destination" to true,
            "sender" to true,
            "receiver" to true,
            "contentType" to false
        )

        val ROUTE_SEPARATOR = ":"

        /**
         * Verifies dsl headers against the supported.
         */
        fun checkHeaderConsistency(messageHeaders : List<String>) {
            if (!messageHeaders.containsAll(supportedHeaders.filterValues { it == true }.keys)) {
                throw GRLException("Missing required headers!")
            }
        }

        /**
         * Packs dsl into package.
         */
        fun pack(message: GRLMessage) : GRLPackage {
            checkHeaderConsistency(message.headerMap.keys.toList())
            val grl = message.headerMap["destination"]
            grl ?: throw GRLException("Unable to send dsl due to missing [destination] header!")
            return GRLPackage(grl, message)
        }
    }
}