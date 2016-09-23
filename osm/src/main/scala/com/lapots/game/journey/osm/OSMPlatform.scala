package com.lapots.game.journey.osm

import java.util.UUID

import com.lapots.game.journey.osm.api.IStateful
import com.lapots.game.journey.osm.domain.Transition

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

  /**
    * Changes object next state according to function.
    *
    * @param id object id
    */
  def nextState(id: String): Unit = {
    OSMContext.changeState(id)
  }

  /**
    * Changes object state manually.
    * @param id object id
    * @param transition transition function
    */
  def manualNextState(id: String, transition: Transition): Unit = {
    OSMContext.manualChangeState(id, transition)
  }
}
