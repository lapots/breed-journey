package com.lapots.game.journey.framework.example

import com.lapots.game.journey.framework.osm.GIndexedStateMachine
import com.lapots.game.journey.framework.osm.GObjectState

/**
 * Example for OSM in Groovy.
 */

class SimpleObject {
    def a = "5"
    def b = 10

    String toString() {
        "field[a]=$a, field[b]=$b"
    }
}

def stateMachine = new GIndexedStateMachine()
def obj = new SimpleObject()
def state1 = new GObjectState(obj, ["a", "b"], [:])
def state2 = new GObjectState(obj, ["a", "b"], ["a" : "111", "b" : null])

stateMachine.add_state(state1) // first is default as index is 0
stateMachine.add_state(state2)

// current states 0 1
println obj // original [0]
stateMachine.next_state()
println obj // after switching state forward [1]
stateMachine.previous_state()
println obj // after switching state backward [0]
stateMachine.previous_state() // should become as it reached -1 and became [1]
println obj // state [1]
