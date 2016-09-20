package com.lapots.game.journey.ims.domain

import org.junit.jupiter.api.Assertions.*

import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*

import com.lapots.game.journey.ims.api.IGRLMultipart

data class DummyMultipart(val field: String) : IGRLMultipart {
    override fun getContent() : Any {
        return this
    }
}

/**
 * Test for {@link GRLMessage}.
 */
class GRLMessageTest: Spek({

    describe("GRL message test 1") {
        val multipart = DummyMultipart("dummy")
        val headers = mapOf(
                Pair("contentType", "object"),
                Pair("objectType", "DummyMultipart")
        )
        val method = GRLProtocol.GRLMethod.POST

        it("should build expected GRL message") {
            val grlMessage = GRLMessage().message {
                method { GRLProtocol.GRLMethod.POST }
                headers {
                    header { "contentType" to "object" }
                    header { "objectType" to "DummyMultipart" }
                }
                multipart { DummyMultipart("dummy") }
            }
            assertEquals(multipart, grlMessage.multipartObject)
            assertEquals(method, grlMessage.methodType)
            assertEquals(headers, grlMessage.headerMap)
        }
    }

    describe("GRL message test 2") {
        val message = GRLMessage()

        it("should create empty object by default") {
            assertTrue(message.isEmpty())
        }
    }
})
