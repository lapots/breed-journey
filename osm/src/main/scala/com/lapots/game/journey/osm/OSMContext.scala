package com.lapots.game.journey.osm

import com.lapots.game.journey.osm.api.IStateful

/**
  * Main context for Object State Machine.
  * It is a singleton.
  */
object OSMContext {
  // stores state machines for objects
  var objectRegistry : Map[String, IStateful] = Map()

  def registerObject(iStateful: IStateful, id: String): Unit = {
    objectRegistry += (id -> iStateful)
  }

  def changeState(id: String): Unit = {
    objectRegistry(id).nextState()
  }

  def retrieveObject(id: String): IStateful[AnyRef] = {
    objectRegistry(id)
  }
}
