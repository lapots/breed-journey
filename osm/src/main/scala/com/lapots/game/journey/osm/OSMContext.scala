package com.lapots.game.journey.osm

import com.lapots.game.journey.osm.api.IStateful

/**
  * Main context for Object State Machine.
  * It is a singleton.
  */
object OSMContext {
  var map : Map[String, IStateful] = Map()

  def registerObject(iStateful: IStateful, id: String): Unit = {
    map += id.-> { iStateful }
  }

  def changeState(id: String): Unit = {
    map(id).nextState()
  }

  def retrieveObject(id: String): IStateful = {
    map(id)
  }
}
