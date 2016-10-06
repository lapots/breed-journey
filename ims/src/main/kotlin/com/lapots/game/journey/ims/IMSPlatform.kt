package com.lapots.game.journey.ims

import com.lapots.game.journey.ims.api.IRouter
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLProtocol
import com.lapots.game.journey.ims.domain.IMSObject
import java.util.*

/**
 * Global wrapper for IMS.
 */
class IMSPlatform {
    companion object {
        fun registerObject(obj: IMSObject) : String {
            if (obj.imsId.isBlank()) { obj.imsId =  UUID.randomUUID().toString() }
            IMSContext.instance.registerObject(obj)
            obj.start()
            return obj.imsId
        }

        fun registerRouter(router : IRouter) {
            IMSContext.instance.registerRouter(router)
        }

        fun transfer(message: GRLMessage) {
            synchronized(this, {
                try {
                    // GRLProtocol.checkHeaderConsistency(message.headerMap.keys.toList())
                    IMSContext.instance.transfer(message)
                } catch (e : IMSException) {
                    println("Exception: ${e.message}")
                    IMSPlatform.stopPlatform(true)
                }
            })
        }

        fun retrieveObject(id: String) : IMSObject {
            return IMSContext.instance.retrieveObject(id)
        }

        fun stopPlatform(clean : Boolean) {
            IMSContext.instance.stopContext(clean)
        }
    }
}