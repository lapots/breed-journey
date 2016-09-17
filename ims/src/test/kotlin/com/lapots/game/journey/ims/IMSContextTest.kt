package com.lapots.game.journey.ims

import com.lapots.game.journey.IMSContext
import com.lapots.game.journey.ims.api.IChannel
import com.lapots.game.journey.ims.api.IRouter
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLPackage
import com.natpryce.hamkrest.assertion.assertThat // well assertFailsWith is fine too
import com.natpryce.hamkrest.throws
import org.junit.jupiter.api.Assertions.*

import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*

/**
 * Test for {@link IMSContext}.
 */
class IMSContextTest : Spek ({
    describe("IMSContext singleton test") {
        it("should create singleton") {
            assertNotNull(IMSContext.instance)
        }
    }

    describe("IMSContext routing test") {
        val ims = IMSContext.instance
        val stubRouter = IRouter.stubRouter()
        val route = "ui:component"
        on("creating package to transfer") {
            val pack = GRLPackage(route + ":path", GRLMessage())
            it("should throw IMSException during transfer due to missing router") {
                assertThat({ ims.transfer(pack) }, throws<IMSException>())
            }
        }
        on("populating IMSContext with routers") {
            ims.registerRouter(route, stubRouter)
            on("creating package to transfer") {
                val pack = GRLPackage(route, GRLMessage())
                it("should return expected router process result") {
                    assertEquals(GRLPackage.emptyPackage(), ims.transfer(pack))
                }
            }
        }
    }
})