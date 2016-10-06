@file:JvmName("Example")

package com.lapots.game.journey.ims.example

import com.lapots.game.journey.ims.IMSContext
import com.lapots.game.journey.ims.IMSPlatform
import com.lapots.game.journey.ims.api.*
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.IMSObject
import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL
import java.util.*
import kotlin.reflect.declaredMemberFunctions

class StringMultipart(val content: String) : IGRLMultipart {
    override fun getContent(): Any {
        return content
    }
}

class BasicObject(val name : String) {}

/**
 * Need for custom producing consuming logic cannot be avoided.
 */
class ImsBasicObject : IMSObject {
    override var imsId: String = ""

    constructor(component: Any) : super(component)

    constructor(component: Any, id: String) : super(component) {
        imsId = id
    }

    override fun consume(message: GRLMessage) {
        println(message.multipartObject?.getContent()) // assuming its an object
    }

    override fun produce(): GRLMessage {
        // dlq as no destination no router
        return GRLMessageDSL().dsl {
            multipart { StringMultipart("$imsId send a message") }
        }
    }

    override fun produce(destination: String): GRLMessage {
        return GRLMessageDSL().dsl {
            header { "route" to "ui" }
            header { "destination" to destination }
            multipart { StringMultipart("$imsId Pigeon~") }
        }
    }

}

class ImsBasicRouter : IRouter {
    // come with idea how to use it
    var routes : List<String>  = ArrayList()

    override fun registerRoute(route: String) {
        routes += route
    }

    override fun supportedRoutes(): List<String> {
        return routes
    }

    override fun processMessage(msg: GRLMessage) {
        val destination = msg.headerMap["destination"]
        val destObject = IMSPlatform.retrieveObject(destination!!)
        // put into processing queue
        destObject.objectMessageQueue += msg
    }
}

fun main(args: Array<String>) {
    val senderObject = BasicObject("Sender object")
    val imsObject1 = ImsBasicObject(senderObject)
    imsObject1.imsId = "SENDER_1"

    val receiverObject = BasicObject("Receiver object")
    val imsObject2 = ImsBasicObject(receiverObject, "RECEIVER_1")

    IMSPlatform.registerObject(imsObject1)
    IMSPlatform.registerObject(imsObject2)

    val imsRouter1 = ImsBasicRouter()
    imsRouter1.registerRoute("ui:")
    IMSPlatform.registerRouter(imsRouter1)

    val msg1 = imsObject1.produce()
    val msg2 = imsObject1.produce("RECEIVER_1")
    IMSPlatform.transfer(msg1)
    IMSPlatform.transfer(msg2)

    val msg3 = imsObject2.produce("SENDER_1")
    IMSPlatform.transfer(msg3)

    IMSPlatform.stopPlatform(true)
}
