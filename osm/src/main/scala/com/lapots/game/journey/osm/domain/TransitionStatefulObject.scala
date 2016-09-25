package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.api.AbstractStatefulObject

/**
  * Stateful object implementation for finite state transition.
  */
class TransitionStatefulObject[T <: AnyRef] extends AbstractStatefulObject[T] {
  override var id: String = _
  override var obj: T = _
  override var currentState: ObjectState = _
  override var currentIndex: Int = _

  var transitions : Array[Transition] = _

  /**
    * Returns stored object.
    *
    * @return raw stored object
    */
  override def getContent(): T = {
    obj
  }

  /**
    * Register available object transitions.
 *
    * @param transition object transition
    */
  def registerTransition(transition: Transition): Unit = {
    transition +: transitions
  }

  /**
    * Initialize object.
    * @param instance object instance
    */
  override def init(instance: T, fields: List[String]): Unit = {
    obj = instance
  }

  /**
    * Returns current object state.
    *
    * @return instance of state
    */
  override def getState(): ObjectState = {
    currentState
  }

  /**
    * Switch object to next state.
    *
    * @return instance of state
    */
  override def nextState(): ObjectState = {
    // apply transition
    currentIndex += 1
    if (currentIndex >= transitions.length) currentIndex = 0
    currentState // temporary
  }
}
