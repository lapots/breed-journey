package com.lapots.game.journey.ims.domain

import org.junit.jupiter.api.Assertions.assertEquals

import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*

import com.lapots.game.journey.ims.api.IGRLMultipart

data class DummyMultipart(val field: String) : IGRLMultipart {
    override fun getContent() {
        this
    }
}

class GRLMessageTest: Spek({
    describe("GRL message builder test") {
        val multipart = DummyMultipart("dummy")
        val headers = mapOf(
                Pair("contentType", "object"),
                Pair("objectType", "DummyMultipart")
        )
        val method = GRLMethod.POST

        it("should build expected GRL message") {
            val grlMessage = GRLMessage().message {
                method { GRLMethod.POST }
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
})
