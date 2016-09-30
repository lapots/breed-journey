package com.lapots.game.journey.osm.exception

/**
  * Exception for OSM errors.
  * @param msg message
  */
case class OSMException(msg: String) extends Exception(msg) {
}
