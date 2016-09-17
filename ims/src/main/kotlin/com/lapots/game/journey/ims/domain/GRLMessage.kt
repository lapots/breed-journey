package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IEmptyObject
import com.lapots.game.journey.ims.api.IGRLMultipart

/**
 * Global Resource Locator message.
 * Special object which represents message in IMS.
 */
class GRLMessage : IEmptyObject {
    val headerMap = mutableMapOf<String, String>()
    var methodType : GRLMethod? = null
    var multipartObject : IGRLMultipart? = null

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

    override fun isEmpty() : Boolean {
        return headerMap.isEmpty() && methodType == null && multipartObject == null
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GRLMessage

        if (headerMap != other.headerMap) return false
        if (methodType != other.methodType) return false
        if (multipartObject != other.multipartObject) return false

        return true
    }

    override fun hashCode(): Int{
        var result = headerMap.hashCode()
        result = 31 * result + (methodType?.hashCode() ?: 0)
        result = 31 * result + (multipartObject?.hashCode() ?: 0)
        return result
    }


}
