package com.lapots.game.journey.osm.api

import com.lapots.game.journey.osm.domain.{ObjectState, Transition}

/**
  * Interface for objects that support states.
  */
trait IStateful extends IOSMContainer {
  /**
    * Returns current object state.
    * @return instance of state
    */
  def getState() : ObjectState

  /**
    * Switch object to next state.
    * @return instance of state
    */
  def nextState() : ObjectState

  /**
    * Returns to previous state.
    * Currently unsupported.
    * @return instance of state
    */
  def previousState() : ObjectState = ???
}
