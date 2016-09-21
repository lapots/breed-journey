package com.lapots.game.journey.ims.domain.dsl

import com.lapots.game.journey.ims.api.IGRLMultipart
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLProtocol

/**
 * Allows to create GRLMessages in DSL-like way
 * Example
 *
        val grlMessage = GRLMessage().dsl {
            method { GRLProtocol.GRLMethod.POST }
            headers {
                header { "contentType" to "object" }
                header { "objectType" to "DummyMultipart" }
            }
            multipart { DummyMultipart("dummy") }
        }
 *
 *
 */
class GRLMessageDSL : GRLMessage() {

    fun dsl(closure: GRLMessageDSL.() -> Unit) : GRLMessage {
        closure()
        return this
    }

    fun method(closure: GRLMessageDSL.() -> GRLProtocol.GRLMethod) : GRLMessage {
        methodType = closure()
        return this
    }

    fun headers(closure: GRLMessageDSL.() -> Unit) : GRLMessage {
        closure()
        return this
    }

    fun header(closure: GRLMessageDSL.() -> Pair<String, String>) : GRLMessage {
        headerMap += closure()
        return this
    }

    fun multipart(closure: GRLMessageDSL.() -> IGRLMultipart) : GRLMessage {
        multipartObject = closure()
        return this
    }
}