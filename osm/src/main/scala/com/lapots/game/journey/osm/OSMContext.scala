package com.lapots.game.journey.osm

import com.lapots.game.journey.osm.domain.ObjectState
import com.lapots.game.journey.osm.exception.OSMException

/**
  * Main context for Object State Machine.
  * It is a singleton.
  */
object OSMContext {
  // in mutable we trust -> either var or scala.collection.mutable.Map
  var registry : Map[String, ObjectState] = Map()

  def registerObject(id: String, objectStateInstance: ObjectState): Unit = {
    if (registry.exists(_._1 == id)) { throw OSMException("Object with this ID already exist!") }
    registry += id -> objectStateInstance
  }

  def retrieveObject(id: String) : ObjectState = {

    registry(id)
  }
}
