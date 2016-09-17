package com.lapots.game.journey.ims.domain

import com.lapots.game.journey.ims.api.IEmptyObject

/**
 * Special transport object.
 * Can be represented as:
 *      grl : 'ui:innerId'
 *      message :
 *          method : POST
 *          headers :
 *              contentType : String
 *          multipart :
 *              'Say hello to innerId'
 */
data class GRLPackage(val grl : String, val message : GRLMessage) : IEmptyObject {
    override fun isEmpty() : Boolean {
        return grl.isBlank() && message.isEmpty()
    }

    // maybe change to singleton or use NullObject pattern
    companion object {
        fun emptyPackage() : GRLPackage {
            return GRLPackage("", GRLMessage())
        }
    }
}