@file:JvmName("Example")

package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.IMSContext
import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.IMSGate
import com.lapots.game.journey.ims.api.*
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLMethod
import com.lapots.game.journey.ims.domain.GRLPackage
import com.lapots.game.journey.ims.domain.IMSObject

class StringMultipart(val content: String) : IGRLMultipart {
    override fun getContent(): Any {
        return content
    }
}

class ExampleChannel : IChannel {
    override fun processMessage(message: GRLMessage) {
        val imsObject = IMSContext.instance.retrieveObject(message.headerMap["receiver"])
        imsObject.objectMessageQueue.offer(message) // put it to IMS object queue
    }
}

class ExampleRouter : IRouter {
    val channels  = mutableMapOf<GRLMethod, IChannel>()

    override fun process(pack : GRLPackage) {
        val msg = pack.message
        val channel = channels[msg.methodType]
        channel!!.processMessage(msg)
    }

    override fun registerChannel(name : GRLMethod, channel : IChannel) {
        if (channels[name] != null) {
            throw IMSException("This channel already exist!")
        }
        channels[name] = channel
    }
}

class ExampleObject(val name: String) : IIMSProducer, IIMSConsumer {
    var identifier = "" // annoying
    var goalId = "" // annoying

    override fun setImsId(id: String) {
        identifier = id
    }

    override fun getImsId(): String {
        return identifier
    }

    override fun produce(): GRLMessage {
        return GRLMessage().message {
            method { GRLMethod.POST }
            multipart { StringMultipart("Hello from $identifier") }
            headers { // just for more readability
                header { "destination" to "ui:component" }
                header { "receiver" to goalId }
                header { "sender" to identifier } // say my name
            }
        }
    }

    override fun consume(message: GRLMessage) {
        // resolve issue with duplicating but I think it something with blocking queue
        println("I $identifier ate the message!")
        var sender = message.headerMap["sender"]
        if (sender != null) { // add check on higher levels
            goalId = sender
        } else {
            throw IMSException("Anonymous messenger")
        }
        println("Received message: ${message.multipartObject?.getContent().toString() }")
        IMSGate.warp(this.produce()) // response
    }
}

fun main(args: Array<String>) {
    // create basic object
    val obj1 = ExampleObject("Object 1")
    val obj2 = ExampleObject("Object 2")

    // create IMS objects
    val imsContext = IMSContext.instance
    val imsObj1 = IMSObject(ExampleObject("Object 1"))
    val imsObj2 = IMSObject(ExampleObject("Object 2"))

    // register objects
    val obj1Id = imsContext.registerObject(imsObj1)
    obj1.setImsId(obj1Id)
    val obj2Id = imsContext.registerObject(imsObj2)
    obj2.setImsId(obj2Id)

    // here is issue #1 -> need to find object to send and set ID specifically
    obj1.goalId = obj2Id

    // here is issue #2 -> need to start object lives
    // give life to objects
    imsObj1.start()
    imsObj2.start()

    // register channel
    val router = ExampleRouter()
    val channel = ExampleChannel()
    router.registerChannel(GRLMethod.POST, channel)

    // issue #3 -> cannot initialize router with routes and had to do it during the register process
    // register router with channels
    IMSContext.instance.registerRouter("ui:component", router)

    // simulation of main application loop
    var limit = 5
    while (limit != 0) {
        // warp is cool
        IMSGate.warp(obj1.produce())
        Thread.sleep(10000)
        --limit
    }
    imsObj1.stopProcessing()
    imsObj2.stopProcessing()
}
