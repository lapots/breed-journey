package com.lapots.game.journey.framework.example

import com.lapots.game.journey.framework.ims.GMessage
import com.lapots.game.journey.ims.domain.GRLProtocol

/**
 * Examples for IMS using Groovy.
 */

def msg = new GMessage()
msg {
    method { GRLProtocol.GRLMethod.POST }
    multipart { "hello world" }
    header { ["destination" : "none"] }
}

println msg