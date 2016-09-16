package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IGRLMultipart

import org.junit.Assert.*
import org.junit.*

class GRLMessageTest {

    data class DummyMultipart(val field: String) : IGRLMultipart {
        override fun getContent() {
            this
        }
    }

    @Test fun grlMessageBuilderTest() {
        val grlMessage = GRLMessage().message {
            method { GRLMethod.POST }
            headers {
                header { Pair("contentType", "object") }
                header { Pair("objectType", "DummyMultipart") }
            }
            multipart { DummyMultipart("dummy") }
        }

        val multipart = DummyMultipart("dummy")
        val headers = mapOf(
                Pair("contentType", "object"),
                Pair("objectType", "DummyMultipart")
        )
        val method = GRLMethod.POST

        assertEquals(multipart, grlMessage.multipartObject)
        assertEquals(method, grlMessage.methodType)
        assertEquals(headers, grlMessage.headerMap)
    }
}
