package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IGRLMultipart

fun MutableMap<String, String>.put(pair : Pair<String, String>) {
    this.put(pair.first, pair.second)
}

class GRLMessage {
    var headerMap : MutableMap<String, String> = mutableMapOf()
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
        headerMap.put(closure())
        return this
    }

    fun multipart(closure: GRLMessage.() -> IGRLMultipart) : GRLMessage {
        multipartObject = closure()
        return this
    }
}
