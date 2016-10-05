package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IIMSConsumer
import com.lapots.game.journey.ims.api.IIMSIdentifiable
import com.lapots.game.journey.ims.api.IIMSProducer
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
abstract class IMSObject : Thread, IIMSConsumer, IIMSProducer {
    constructor(component : Any) {
        obj = component
    }

    // I did not test but I think I can put object into queue directly
    // from the channel.
    val objectMessageQueue = LinkedBlockingDeque<GRLMessage>()
    val obj : Any
    var isRunning = true

    override fun run() {
        while(isRunning) {
            Thread.sleep(1000) // much intense. need a collar olol
            processMessages()
        }
    }

    fun stopProcessing() {
        println("Stopping $imsId")
        isRunning = false
    }

    // thread that waits for messages
    // basically consuming messages
    fun processMessages() {
        while (!objectMessageQueue.isEmpty()) {
            consume(objectMessageQueue.remove())
        }
    }
}
