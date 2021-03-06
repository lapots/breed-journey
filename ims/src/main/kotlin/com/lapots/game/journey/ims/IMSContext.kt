package com.lapots.game.journey.ims

import com.lapots.game.journey.ims.api.*
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLPackage
import com.lapots.game.journey.ims.domain.GRLProtocol
import com.lapots.game.journey.ims.domain.IMSObject
import java.util.*

/**
 * Main context method.
 * Main entry point for IMS.
 */
class IMSContext {
    val routes = mutableMapOf<String, IRouter>()
    val imsObjects = mutableMapOf<String, IMSObject>()

    private object IMSContextHolder {
        val instance = IMSContext()
    }

    companion object {
        val instance : IMSContext by lazy { IMSContextHolder.instance }
    }

    /**
     * Registers route in IMS.
     */
    fun registerRouter(router : IRouter) {
        router.supportedRoutes().forEach { route ->
            println (route)
            if (routes[route] != null) {
                throw IMSException("Such route already registered under some another router!")
            }
            routes[route] = router
        }
    }

    /**
     * Registers IMS object in IMS context.
     */
    fun registerObject(obj : IMSObject) { imsObjects[obj.imsId] = obj }

    /**
     * Returns IMS object by id.
     */
    fun retrieveObject(id : String?) : IMSObject {
        val imsObject = imsObjects[id]
        imsObject ?: throw IMSException("Object with that id : $id does not exist!")
        return imsObject
    }

    fun stopContext(clean : Boolean) {
        imsObjects.forEach { it.value.stopProcessing() }
        if (clean) {
            imsObjects.clear()
        }
    }

    fun transfer(message: GRLMessage) {
        val route = message.headerMap["route"]
        if (route != null) { // might as well as DLQ
            val router = specifyRouter(route)
            router ?: throw IMSException("No router found for $route")
            router.processMessage(message)
        }
    }

    /**
     * Returns corresponding router according to GRL.
     *
     * What is special there is that it tries to find router
     * using the most detailed grl - original and then slowly
     * decrease the level of details.
     *
     * For example let's assume we've got routes for [ui:component:slice, ui:processor, ui:components, ui]
     *      'ui:component:data' iterations
     *              'ui:component:data' -> no match
     *              'ui:component' -> no match
     *              'ui' -> match found
     * So the dsl will be delegated to router with 'ui' path
     */
    private fun specifyRouter(grlParam : String) : IRouter? {
        var grl = grlParam
        var router : IRouter? = null
        // make it end with : like ui:route -> ui:route:
        if (!grl.endsWith(GRLProtocol.ROUTE_SEPARATOR)) {
            grl += GRLProtocol.ROUTE_SEPARATOR
        }
        while (grl.indexOf(GRLProtocol.ROUTE_SEPARATOR) != -1) {
            router = routes[grl]
            if (router == null) {
                grl = grl.slice(0..grl.lastIndexOf(GRLProtocol.ROUTE_SEPARATOR) - 1)
            } else {
                break
            }
        }
        return router
    }
}
