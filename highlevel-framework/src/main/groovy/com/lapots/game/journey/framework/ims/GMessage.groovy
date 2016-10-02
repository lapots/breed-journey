package com.lapots.game.journey.framework.ims

import com.lapots.game.journey.ims.api.IGRLMultipart
import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL

/**
 * Wrapper over kotlin DSL.
 *
 * Basically I can make GRLMessageDSL open class
 * and the inherit it and override methods with
 * reference to super-class.
 */
class GMessage {
    def grlMessageDSL = new GRLMessageDSL()

    def call(closure) {
        closure.delegate = this
        closure.setResolveStrategy Closure.DELEGATE_ONLY

        closure()
        grlMessageDSL
    }

    def method(closure) {
        grlMessageDSL.dsl {
            grlMessageDSL.method {
                closure()
            }
        }
        this
    }

    def header(closure) {
        grlMessageDSL.dsl {
            grlMessageDSL.header {
                KotlinBridgeUtils.convertSingleMapToPair(closure())
            }
        }
        this
    }

    def multipart(closure) {
        grlMessageDSL.dsl {
            grlMessageDSL.multipart {
                wrap_multipart(closure())
            }
        }
        this
    }

    static def wrap_multipart(content) {
        new IGRLMultipart() {
            @Override
            Object getContent() {
                return content
            }
        }
    }
}
