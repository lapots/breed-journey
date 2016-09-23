package com.lapots.game.journey.osm.api

import com.lapots.game.journey.osm.domain.{State, Transition}

/**
  * Object wrapper for external objects.
  *
  * Basically it is the state machine itself.
  *
  * @tparam T any object type
  */
abstract class AbstractStatefulObject[T <: scala.AnyRef] extends IStateful {
  var obj : T
  // in current implementation I'll go with linear states
  var states : Array[State]
  // represents transition functions for every state
  // basically state1 -> transition1 -> state2 -> transition2 -> state3 -> transition3 -> state1
  var transitionFunctions : Array[Transition]
}
