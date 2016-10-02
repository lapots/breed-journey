package com.lapots.game.journey.framework.osm

import com.lapots.game.journey.osm.domain.ObjectState

/**
 * Transforming Groovy objects into Scala objects.
 */
class ScalaBridgeUtils {

    // converts Groovy list to Scala list
    static def convertList(list) {
        scala.collection.JavaConverters.collectionAsScalaIterable(list).toList()
    }
}
