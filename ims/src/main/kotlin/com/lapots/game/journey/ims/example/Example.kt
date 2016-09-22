@file:JvmName("Example")

package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.IMSContext
import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.IMSPlatform
import com.lapots.game.journey.ims.api.*
import com.lapots.game.journey.ims.domain.*
import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL
import java.util.*

class StringMultipart(val content: String) : IGRLMultipart {
    override fun getContent(): Any {
        return content
    }
}

class ExampleChannel : IChannel {

    override fun process(message: GRLMessage) {
        val imsObject = IMSContext.instance.retrieveObject(message.headerMap["receiver"])
        imsObject.objectMessageQueue.offer(message) // put it to IMS object queue
    }

}

class ExampleRouter(val routes: MutableMap<String, String>) : IRouter {
    val channels  = mutableMapOf<GRLProtocol.GRLMethod, IChannel>()

    override fun process(pack : GRLPackage) {
        val msg = pack.message
        val channel = channels[msg.methodType]
        channel?.process(msg)
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

class BasicObject(val name : String) {}

class ExampleObject(val toWrap : BasicObject) : AbstractIdentifiable<BasicObject>() {
    override val obj = toWrap
    override var imsId = ""
    var goalId = "" // annoying

    override fun produce(): GRLMessage {
        return GRLMessageDSL().dsl {
            method { GRLProtocol.GRLMethod.POST }
            multipart { StringMultipart("Hello from $imsId") }
            headers { // just for more readability
                header { "destination" to "ui:component" }
                header { "receiver" to goalId }
                header { "sender" to imsId } // say my name
            }
        }
    }

    override fun util_produce(destination: String): GRLMessage {
        goalId = destination
        return produce()
    }

    override fun consume(message: GRLMessage) {
        GRLProtocol.checkMessageConsistency(message, this)
        println("I $imsId ate the message!")
        var sender = message.headerMap["sender"]
        if (sender != null) { // add check on higher levels
            goalId = sender
        } else {
            throw IMSException("Anonymous messenger")
        }
        println("Received message: ${message.multipartObject?.getContent().toString() }")
    }
}

fun List<String>.randomFromList() : String {
    return this[Random().nextInt(this.size)]
}

fun main(args: Array<String>) {
    // create basic object
    val objects = 10
    var index = 0
    var id  = ""
    var indecies = mutableListOf<String>()
    while (index < objects) {
        val input = BasicObject("obj")
        val obj = ExampleObject(input)
        val idN = IMSPlatform.registerObject(obj)
        if (id.isNotEmpty()) {
            obj.goalId = id
        }
        id = idN
        index++
        indecies.add(idN)
    }

    // register channel
    val router = ExampleRouter(mutableMapOf("ui:component" to "ui:component")) // weird
    val channel = ExampleChannel()
    router.registerChannel(GRLProtocol.GRLMethod.POST, channel)

    // objects on the same route
    IMSPlatform.registerRouter(router)

    // imitation hard work
    val initialTime = System.currentTimeMillis()
    var currentTime = System.currentTimeMillis()
    while ((currentTime - initialTime) < 10000) {
        currentTime = System.currentTimeMillis()
        val producerId = indecies.randomFromList()
        val consumerId = indecies.randomFromList()
        if (producerId != consumerId) {
            IMSPlatform.transfer(IMSPlatform.util_produce(producerId, consumerId))
        }
        Thread.sleep(100)
    }

    IMSPlatform.stopPlatform(true)
}
