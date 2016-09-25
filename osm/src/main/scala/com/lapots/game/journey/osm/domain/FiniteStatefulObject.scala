package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.api.AbstractStatefulObject

/**
  * Stateful object implementation for finite object state.
  * @tparam T any object type
  */
class FiniteStatefulObject[T <: AnyRef] extends AbstractStatefulObject[T] {
  override var id: String = _
  override var obj: T = _
  override var currentState: ObjectState = _
  override var currentIndex: Int = _

  // all possible object states
  var states : Array[ObjectState] = _

  /**
    * Return referenced object.
    * @return raw application object
    */
  override def getContent(): T = {
    obj
  }

  /**
    * Registers possible object state.
    *
    * @param state new possible state
    */
  def registerState(state: ObjectState): Unit = {
    states :+ state
  }

  /**
    * Initialize object.
    * @param instance object instance
    */
  override def init(instance: T, fields: List[String]): Unit = {
    obj = instance
    // create initial state
    currentState = new ObjectState
    fields.foreach(item =>
      currentState.objectParameters += (item -> obj.getClass.getField(item).get())
    )
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
    currentIndex += 1
    if (currentIndex >= states.length) currentIndex = 0
    currentState = states(currentIndex)
    // apply state
    currentState.Action.applyState(currentState, id)
    // now return
    currentState
  }
}
