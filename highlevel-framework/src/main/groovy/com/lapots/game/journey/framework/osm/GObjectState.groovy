package com.lapots.game.journey.framework.osm

import com.lapots.game.journey.framework.interop.StaticScalaInterop
import com.lapots.game.journey.osm.OSMPlatform
import com.lapots.game.journey.osm.domain.ObjectState

/**
 * Wrapper over Scala object state instance.
 */
class GObjectState {
    def objectState

    public GObjectState(anyObj, stateFields, initialState) {
        scala.collection.immutable.List fields = ScalaBridgeUtils.toScalaList(stateFields)
        scala.collection.immutable.Map map = ScalaBridgeUtils.toScalaMap(initialState)
        // in case of Scala I think we could use implicits for the parameters
        def id = OSMPlatform.registerObject(anyObj, fields, map)
        objectState = OSMPlatform.retrieveObject(id)
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
