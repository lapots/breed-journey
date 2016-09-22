package com.lapots.game.journey.ims

import com.lapots.game.journey.ims.api.IChannel
import com.lapots.game.journey.ims.api.IIMSIdentifiable
import com.lapots.game.journey.ims.api.IIMSProducer
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
        // seems fishy
        fun registerObject(obj: IIMSIdentifiable) : String {
            val id = UUID.randomUUID().toString() // generate id
            registerObject(obj, id)
            return id
        }

        fun registerObject(obj: IIMSIdentifiable, id : String) {
            obj.imsId = id
            val imsObject = IMSObject(obj)
            IMSContext.instance.registerObject(imsObject)
            imsObject.start()
        }

        fun registerRouter(router : IRouter) {
            IMSContext.instance.registerRouter(router)
        }

        // I think I can do it another way
        fun registerChannel(router: IRouter, channel: IChannel,
                            channelId: GRLProtocol.GRLMethod) {
            router.registerChannel(channelId, channel)
        }

        fun transfer(message: GRLMessage) {
            synchronized(this, {
                GRLProtocol.checkHeaderConsistency(message.headerMap.keys.toList())
                IMSContext.instance.transfer(GRLProtocol.pack(message))
            })
        }

        fun util_produce(producerId : String, consumerId: String) : GRLMessage {
            val imsObject = IMSContext.instance.imsObjects[producerId]?.obj
            if (imsObject is IIMSProducer) {
                val cast = imsObject as IIMSProducer
                return cast.util_produce(consumerId)
            }
            return GRLMessage()
        }

        fun stopPlatform(clean : Boolean) {
            IMSContext.instance.stopContext(clean)
        }
    }
}