package com.lapots.game.journey.osm.api

import com.lapots.game.journey.osm.domain.{State, Transition}

/**
  * Interface for objects that support states.
  */
trait IStateful {
  /**
    * Allows to retrieve object state.
    *
    * @return object state
    */
  def getState() : State

  /**
    * Sets next state for the object.
    */
  def nextState()

  /**
    * Allows to set state for the object
    * by applying transition function.
    *
    * General use for specific transition changes.
    * It affects consistency  however does not affect index
    * so basically using this function we can manually change object state.
    * @param func transition function
    */
  def nextState(func : Transition)
}
