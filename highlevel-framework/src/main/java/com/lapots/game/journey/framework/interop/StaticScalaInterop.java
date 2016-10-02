package com.lapots.game.journey.framework.interop;

import com.lapots.game.journey.osm.domain.ObjectState;

/**
 * Bridge for Scala singleton invocations.
 */
public class StaticScalaInterop {

    // Wrapper for StateMachine
    public static class StateMachine {
        public static void readObjectState(ObjectState objState) {
            // just ignore IDE error
            objState.Mirror().inMirrorObjectState(objState);
        }
    }
}
