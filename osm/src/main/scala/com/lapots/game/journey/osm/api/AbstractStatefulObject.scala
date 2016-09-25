package com.lapots.game.journey.osm.api

import com.lapots.game.journey.osm.domain.{ObjectState, Transition}

/**
  * Object wrapper for external objects.
  *
  * Basically it is the state machine itself.
  *
  * @tparam T any object type
  */
abstract class AbstractStatefulObject[T <: scala.AnyRef] extends IStateful {
  // identifier of the object in OSM
  var id : String
  // reference to existing object
  var obj : T
  // current object state
  var currentState : ObjectState
  // offset index
  var currentIndex : Int

  /**
    * Initialize object.
    * @param instance object instance
    */
  def init(instance : T, fields: List[String])
}
