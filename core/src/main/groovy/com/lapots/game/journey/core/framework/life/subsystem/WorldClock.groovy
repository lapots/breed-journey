package com.lapots.game.journey.core.framework.life.subsystem

import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.util.MathUtils;

class WorldClock extends Thread implements IThreadable {

    def clock_messages = [
        "The world is living...Without a hero",
        "The world is filled with life",
        "The world is eternal"
    ]

    volatile isEternal = true
    @Override
    public void run() {
        while (isEternal) {
            Thread.sleep(10000);
            println MathUtils.randomFromList(clock_messages)
        }
    }

    def stopThread() {
        println "Stopping thread"
        isEternal = false
    }
}
