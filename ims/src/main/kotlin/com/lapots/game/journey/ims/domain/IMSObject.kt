package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.IMSContext
import com.lapots.game.journey.ims.api.IIMSConsumer
import com.lapots.game.journey.ims.api.IIMSIdentifiable
import java.util.concurrent.LinkedBlockingDeque

/**
 * Wrapper for object to use in IMS.
 * Object basically has a queue which is populated by IMS and it processes
 * messages from there in runtime.
 *
 * I presume it will lead to huge performance drop because it will
 * collect messages and then process it - instead of direct invocation
 * from the methods.
 */
class IMSObject : Thread {
    constructor(component : IIMSIdentifiable) {
        obj = component
        val id = IMSContext.instance.registerObject(this)
        obj.setImsId(id)
    }

    // I did not test but I think I can put object into queue directly
    // from the channel.
    val objectMessageQueue = LinkedBlockingDeque<GRLMessage>()
    val obj : IIMSIdentifiable
    var isRunning = true

    override fun run() {
        while(isRunning) {
            processMessages()
        }
    }

    fun stopProcessing() {
        isRunning = false
    }

    // thread that waits for messages
    // basically consuming messages
    fun processMessages() {
        if (obj is IIMSConsumer) {
            // no money no honey
            objectMessageQueue.forEach {
                obj.consume(it) // cannot resolve reference !!!
            }
        }
    }

    fun getObjectId() : String {
        return obj.getImsId() // cannot resolve reference !!!
    }
}
