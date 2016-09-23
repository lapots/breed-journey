package com.lapots.game.journey.osm

import com.lapots.game.journey.osm.api.IStateful
import com.lapots.game.journey.osm.domain.Transition

/**
  * Main context for Object State Machine.
  * It is a singleton.
  */
object OSMContext {
  // stores state machines for objects
  var objectRegistry : Map[String, IStateful] = Map()

  def registerObject(iStateful: IStateful, id: String): Unit = {
    objectRegistry += id.-> { iStateful }
  }

  def changeState(id: String): Unit = {
    objectRegistry(id).nextState()
  }

  def manualChangeState(id: String, transition: Transition): Unit = {
    objectRegistry(id).nextState(transition)
  }

  def retrieveObject(id: String): IStateful = {
    objectRegistry(id)
  }
}
