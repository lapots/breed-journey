package com.lapots.game.journey.osm

import java.util.UUID

import com.lapots.game.journey.osm.domain.ObjectState

/**
  * High level wrapper over OSMContext.
  */
object OSMPlatform {

  def registerObject(objectInstance: AnyRef): String = {
    val id = UUID.randomUUID().toString
    val objState = new ObjectState(objectInstance)

    OSMContext.registerObject(id, objState)
    id
  }

  def registerObject(objectInstance: AnyRef, fields: List[String], state: Map[String, Any]): String = {
    val id = UUID.randomUUID().toString
    val objState = new ObjectState(objectInstance, fields, state)

    OSMContext.registerObject(id, objState)
    id
  }

  // any object - but mostly AnyRef objects I presume
  def registerObject(objectInstance: AnyRef, fields: List[String]): String = {
    val id = UUID.randomUUID().toString
    val objState = new ObjectState(objectInstance, fields)

    OSMContext.registerObject(id, objState)
    id
  }

  def retrieveObject(id: String): ObjectState = {
    OSMContext.retrieveObject(id)
  }
}
