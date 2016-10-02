package com.lapots.game.journey.framework.osm

import com.lapots.game.journey.framework.interop.StaticScalaInterop
import com.lapots.game.journey.osm.OSMPlatform
import com.lapots.game.journey.osm.domain.ObjectState

/**
 * Wrapper over Scala object state instance.
 */
class GObjectState {
    def objectState

    public GObjectState(anyObj, stateFields) {
        def id = OSMPlatform.registerObject(anyObj, ScalaBridgeUtils.convertList(stateFields))
        objectState = OSMPlatform.retrieveObject(id)
    }

    // outMirror
    def writeStateToObject() {
        ObjectState.Mirror$.MODULE$.outMirrorObjectState(objectState)
    }

    // inMirror
    def readObjectToState() {
        StaticScalaInterop.StateMachine.readObjectState(objectState)
    }

    String toString() {
        objectState.objRef.toString()
    }
}
