package com.lapots.game.journey

import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.api.*
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLPackage

/**
 * Main context method.
 * Main entry point for IMS.
 */
class IMSContext {
    private val ROUTE_SEPARATOR = ":"
    private val routes = mutableMapOf<String, IRouter>()

    private object IMSContextHolder {
        val instance = IMSContext()
    }

    companion object {
        val instance : IMSContext by lazy { IMSContextHolder.instance }
    }

    /**
     * Registers route in IMS.
     */
    fun registerRouter(route : String, router : IRouter) {
        if (routes[route] != null) {
            throw IMSException("Such route already registered under some another router!")
        }
        routes[route] = router
    }

    /**
     * Transfer object.
     * The result is returned as GRLPackage or IMSException is thrown.
     */
    fun transfer(pack : GRLPackage) : GRLPackage {
        // get corresponding router
        val router = specifyRouter(pack.grl)
        router ?: throw IMSException("Cannot transfer message due to missing route processor!")
        // process package
        return router.process(pack)
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
     * So the message will be delegated to router with 'ui' path
     */
    private fun specifyRouter(grlParam : String) : IRouter? {
        var grl = grlParam
        var router : IRouter? = null
        // make it end with : like ui:route -> ui:route:
        if (!grl.endsWith(ROUTE_SEPARATOR)) {
            grl += ROUTE_SEPARATOR
        }
        while (grl.indexOf(ROUTE_SEPARATOR) != -1) {
            router = routes[grl]
            if (router == null) {
                grl = grl.slice(0..grl.lastIndexOf(ROUTE_SEPARATOR) - 1)
            } else {
                break
            }
        }
        print("Router found!")
        return router
    }
}
