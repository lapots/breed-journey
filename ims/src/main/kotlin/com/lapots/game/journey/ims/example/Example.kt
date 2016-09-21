@file:JvmName("Example")

package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.IMSContext
import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.IMSGate
import com.lapots.game.journey.ims.api.*
import com.lapots.game.journey.ims.domain.*
import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL

class StringMultipart(val content: String) : IGRLMultipart {
    override fun getContent(): Any {
        return content
    }
}

class ExampleChannel : IChannel {
    override fun processMessage(message: GRLMessage) {
        // replace with some external storage
        val imsObject = IMSContext.instance.retrieveObject(message.headerMap["receiver"])
        imsObject.objectMessageQueue.offer(message) // put it to IMS object queue
    }
}

class ExampleRouter(val routes: MutableMap<String, String>) : IRouter {
    val channels  = mutableMapOf<GRLProtocol.GRLMethod, IChannel>()

    override fun process(pack : GRLPackage) {
        val msg = pack.message
        val channel = channels[msg.methodType]
        channel!!.processMessage(msg)
    }

    override fun getRoutes() : List<String> {
        return routes.keys.toList()
    }

    override fun registerRoute(route : String) {
        routes[route] = route // lol
    }

    override fun isSupport(route: String) : Boolean {
        return routes[route] != null
    }

    override fun registerChannel(name : GRLProtocol.GRLMethod, channel : IChannel) {
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
        return GRLMessageDSL().dsl {
            method { GRLProtocol.GRLMethod.POST }
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
        println("I $identifier ate the dsl!")
        var sender = message.headerMap["sender"]
        if (sender != null) { // add check on higher levels
            goalId = sender
        } else {
            throw IMSException("Anonymous messenger")
        }
        println("Received dsl: ${message.multipartObject?.getContent().toString() }")
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

    // register channel
    val router = ExampleRouter(mutableMapOf("ui:component" to "ui:component")) // weird
    val channel = ExampleChannel()
    router.registerChannel(GRLProtocol.GRLMethod.POST, channel)

    // register router with channels
    IMSContext.instance.registerRouter(router)

    // register objects
    val obj1Id = imsContext.registerObject(imsObj1)
    obj1.setImsId(obj1Id)
    val obj2Id = imsContext.registerObject(imsObj2)
    obj2.setImsId(obj2Id)

    obj1.goalId = obj2Id // well it should know some id anyway

    // simulation of main application loop
    imsObj1.start()
    imsObj2.start()
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
