package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IGRLMultipart

class GRLMessage {
    val headerMap = mutableMapOf<String, String>()
    lateinit var methodType : GRLMethod
    lateinit var multipartObject : IGRLMultipart

    fun message(closure: GRLMessage.() -> Unit) : GRLMessage {
        closure()
        return this
    }

    fun method(closure: GRLMessage.() -> GRLMethod) : GRLMessage {
        methodType = closure()
        return this
    }

    fun headers(closure: GRLMessage.() -> Unit) : GRLMessage {
        closure()
        return this
    }

    fun header(closure: GRLMessage.() -> Pair<String, String>) : GRLMessage {
        headerMap += closure()
        return this
    }

    fun multipart(closure: GRLMessage.() -> IGRLMultipart) : GRLMessage {
        multipartObject = closure()
        return this
    }
}
