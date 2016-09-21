package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IEmptyObject
import com.lapots.game.journey.ims.api.IGRLMultipart

/**
 * Special object which represents GRL message in IMS.
 */
open class GRLMessage : IEmptyObject {
    val headerMap = mutableMapOf<String, String>()
    var methodType : GRLProtocol.GRLMethod? = null
    var multipartObject : IGRLMultipart? = null

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
