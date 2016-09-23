package com.lapots.game.journey.osm

import java.util.UUID

import com.lapots.game.journey.osm.api.IStateful

/**
  * Global wrapper for OSM.
  */
object OSMPlatform {

  /**
    * Register stateful object in OSM and generate id.
    *
    * @param obj stateful object
    * @return generated id (just in case)
    */
  def registerObject(obj : IStateful): String = {
    val id = UUID.randomUUID().toString
    OSMContext.registerObject(obj, id)

    id
  }
}
