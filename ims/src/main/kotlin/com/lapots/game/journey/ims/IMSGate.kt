package com.lapots.game.journey.ims

import com.lapots.game.journey.ims.IMSContext
import com.lapots.game.journey.ims.IMSException
import com.lapots.game.journey.ims.domain.GRLMessage
import com.lapots.game.journey.ims.domain.GRLPackage
import com.lapots.game.journey.ims.domain.GRLProtocol

/**
    --- PROOF OF CONCEPT ---
    This is high level concept of a consumer.
    Expect the concept of producer.

    import java.util.concurrent.*

    // wrapping object as a Callable to do action
    Callable<Integer> task = new Callable() {
        def call()  {
            try {
                TimeUnit.SECONDS.sleep(20)
                return "Responser"
            } catch (InterruptedException e) {
                println "Exception occured"
            }
        }
    }

    // asynchronous controller
    ExecutorService executor = Executors.newFixedThreadPool(1)
    Future<Integer> future = executor.submit(task)

    def result
    // waiting in a loop for response -> processing dsl
    while (true) {
        TimeUnit.SECONDS.sleep(5)
        println "Main loop"
        if (future.isDone()) {
            result = future.get()
            println result
            break
        }
    }
    executor.shutdown()
    println "Ended"
 */

/**
 * Transport gate for IMS.
 */
class IMSGate {
    companion object {
        fun warp(message : GRLMessage) {
            IMSContext.instance.transfer(GRLProtocol.pack(message))
        }
    }
}
