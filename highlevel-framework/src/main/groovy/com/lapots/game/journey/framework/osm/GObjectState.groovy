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
        // register object and write current object state by default
        scala.collection.immutable.List fields = ScalaBridgeUtils.toScalaList(stateFields.keySet())
        scala.collection.immutable.Map fieldState = ScalaBridgeUtils.toScalaMap(stateFields)
        def id = OSMPlatform.registerObject(anyObj, fields, fieldState)
        objectState = OSMPlatform.retrieveObject(id)
        // in case if we provide custom state
    }

    // outMirror
    def writeStateToObject() {
       StaticScalaInterop.StateMachine.writeObjectState(objectState)
    }

    // inMirror
    def readObjectToState() {
        StaticScalaInterop.StateMachine.readObjectState(objectState)
    }

    String toString() {
        objectState.objRef.toString()
    }
}
