package com.lapots.game.journey.osm.api

/**
  * Parent of all OSM objects that can contain objects.
  */
trait IOSMContainer {
  def getContent() : AnyRef
}
